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

    public Integer contarTodosAgendamentosValidosHemocentro(Integer idUsuarioHemocentro){
        return repository.countByIdUsuarioHemocentro_IdAndDataHoraGreaterThanEqual(LocalDateTime.now(), idUsuarioHemocentro);
    }

    public Integer contarTodosAgendamentosValidosDoador(Integer idDoador) {
        return repository.countByIdUsuarioDoador_IdAndDataHoraGreaterThanEqual(LocalDateTime.now(), idDoador);
    }

    public List<Agendamento> buscarTodosPorIdDoador(Integer idDoador) {
        return repository.findByIdUsuarioDoador_Id(LocalDateTime.now(), idDoador);
    }

    public void deletarAgendamento(Integer idAgendamento) {
        repository.deleteById(idAgendamento);
    }
}
