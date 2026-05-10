package com.tccAppBancoDeSangue.BloodLink.repository;

import com.tccAppBancoDeSangue.BloodLink.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    List<Agendamento> findByIdUsuarioHemocentro_Id(Integer idUsuarioHemocentro);
}
