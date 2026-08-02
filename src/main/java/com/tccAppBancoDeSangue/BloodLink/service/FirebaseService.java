package com.tccAppBancoDeSangue.BloodLink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.tccAppBancoDeSangue.BloodLink.model.Agendamento;
import com.tccAppBancoDeSangue.BloodLink.model.Campanha;
import com.tccAppBancoDeSangue.BloodLink.model.Usuario;
import com.tccAppBancoDeSangue.BloodLink.repository.UsuarioRepository;

@Service
public class FirebaseService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void sendNotification(String title, String body, String token) throws FirebaseMessagingException { 
       
        Message message = Message.builder()
        .setNotification(com.google.firebase.messaging.Notification.builder()
        .setTitle(title)
        .setBody(body)
        .build()
        ).setToken(token)
        .build();

        String response = FirebaseMessaging.getInstance().send(message);

        System.out.println(response);
    }   

    public void sendCampaignNotification(Campanha campanha) throws FirebaseMessagingException {
        String title = "Nova Campanha: " + campanha.getNomeCampanha();
        String body = "Venha ver a nova campanha de doação de sangue! Eles precisam do seu tipo sanguíneo: " + campanha.getTipoSanguineoVisado();

        List<Usuario> usuarios = usuarioRepository.findByTipoSanguineo(campanha.getTipoSanguineoVisado());

        List<String> tokens = usuarios.stream()
                .map(Usuario::getTokenFcm)
                .filter(token -> !token.isBlank())
                .toList();

        for (String token : tokens) {
            sendNotification(title, body, token);   
        }
    }

    public void sendNotificationToHemocentro(Agendamento agendamento) throws FirebaseMessagingException {
        String title = "Novo agendamento";
        String body = "O doador " + agendamento.getIdUsuarioDoador().getNome() + " se cadastrou para a campanha " + agendamento.getCampanha().getNomeCampanha() + ". Entre em contato com ele pelo email: " + agendamento.getIdUsuarioDoador().getEmail();

        Usuario hemocentro = agendamento.getIdUsuarioHemocentro();
        hemocentro = usuarioRepository.findById(hemocentro.getId()).orElseThrow(() -> new RuntimeException("Hemocentro não encontrado"));
        String token = hemocentro.getTokenFcm();

        sendNotification(title, body, token);
    }
}
