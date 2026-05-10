package com.tccAppBancoDeSangue.BloodLink.service;

import com.tccAppBancoDeSangue.BloodLink.model.Agendamento;
import com.tccAppBancoDeSangue.BloodLink.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository repository;

    public Agendamento criarAgendamento(Agendamento agendamento){
        return repository.save(agendamento);
    }

    public List<Agendamento> buscarTodosPorId(Integer idUsuarioHemocentro){
        return repository.findByIdUsuarioHemocentro_Id(LocalDateTime.now(), idUsuarioHemocentro);
    }
}
