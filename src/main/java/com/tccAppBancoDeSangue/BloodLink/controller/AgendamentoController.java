package com.tccAppBancoDeSangue.BloodLink.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tccAppBancoDeSangue.BloodLink.service.FirebaseService;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.tccAppBancoDeSangue.BloodLink.dto.AgendamentoCreateDTO;
import com.tccAppBancoDeSangue.BloodLink.model.Agendamento;
import com.tccAppBancoDeSangue.BloodLink.model.Campanha;
import com.tccAppBancoDeSangue.BloodLink.model.Usuario;
import com.tccAppBancoDeSangue.BloodLink.service.AgendamentoService;
import com.tccAppBancoDeSangue.BloodLink.service.CampanhaService;
import com.tccAppBancoDeSangue.BloodLink.service.UsuarioService;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
    private final AgendamentoService service;

    private final UsuarioService serviceUser;

    private final CampanhaService serviceCampanha;

    private final FirebaseService firebaseService;

   public AgendamentoController(FirebaseService firebaseService, CampanhaService serviceCampanha, AgendamentoService service, UsuarioService serviceUser) {
        this.serviceUser = serviceUser;
        this.firebaseService = firebaseService;
        this.serviceCampanha = serviceCampanha;
        this.service = service;
    }

    @PostMapping("/criarAgendamento")
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody AgendamentoCreateDTO dto) throws FirebaseMessagingException{

        if(service.doadorJaParticipaDaCampanha(dto.idUsuarioDoador(),dto.idCampanha())){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setDataHora(dto.data());

        Usuario usuarioDoa = serviceUser.buscarPorId(dto.idUsuarioDoador());
        agendamento.setIdUsuarioDoador(usuarioDoa);

        Usuario usuarioHemo = serviceUser.buscarPorId(dto.idUsuarioHemocentro());
        agendamento.setIdUsuarioHemocentro(usuarioHemo);

        Campanha campanha = serviceCampanha.buscarPorId(dto.idCampanha());
        agendamento.setCampanha(campanha);

        Agendamento agendamentoCriado = service.criarAgendamento(agendamento);

        firebaseService.sendNotificationToHemocentro(agendamento);

        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoCriado);
    }

    @GetMapping("/listarAgendamentos/{idHemo}")
    public List<Agendamento> listarTodosOsAgendamentosAtivos(@PathVariable Integer idUsuarioHemocentro){
        return service.buscarTodosPorId(idUsuarioHemocentro);
    }

    @GetMapping("/agendamentosAtivosHemo/{idHemo}")
    public Integer contarAgendamentosAtivosHemo(@PathVariable Integer idHemo){
        return service.contarTodosAgendamentosValidosHemocentro(idHemo);
    }

    @GetMapping("/contarAgendamentosValidosDoador/{idDoador}")
    public Integer contarAgendamentosAtivosDoador(@PathVariable Integer idDoador){
        return service.contarTodosAgendamentosValidosDoador(idDoador);
    }

    @GetMapping("/agendamentosValidosDoador/{idDoador}")
    public List<Agendamento> listarAgendamentosValidosUsuarioDoador(@PathVariable Integer idDoador){
        return service.buscarTodosPorIdDoador(idDoador);
    }

    @DeleteMapping("/deletarAgendamento/{idAgendamento}")
    public void deletarAgendamento(@PathVariable Integer idAgendamento){
        service.deletarAgendamento(idAgendamento);
    }

    @GetMapping("/participantesCampanhas/{idHemocentro}")
    public Integer contarParticipantesCampanhas(
        @PathVariable Integer idHemocentro){
    return service.contarParticipantesCampanhas(idHemocentro);
    }
}
