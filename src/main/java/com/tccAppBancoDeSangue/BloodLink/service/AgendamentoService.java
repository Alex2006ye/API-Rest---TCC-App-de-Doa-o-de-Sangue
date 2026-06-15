package com.tccAppBancoDeSangue.BloodLink.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tccAppBancoDeSangue.BloodLink.model.Agendamento;
import com.tccAppBancoDeSangue.BloodLink.repository.AgendamentoRepository;

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

    public boolean doadorJaParticipaDaCampanha(Integer idDoador,Integer idCampanha){
    return repository.existsByIdUsuarioDoador_IdAndCampanha_IdCampanha(
            idDoador,idCampanha);
}

public Integer contarParticipantesCampanhas(Integer idHemocentro){
    return repository.contarParticipantesCampanhas(idHemocentro);
}
}
