package com.tccAppBancoDeSangue.BloodLink.repository;

import com.tccAppBancoDeSangue.BloodLink.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    // basicamente, listar todos os agendamentos válidos pela data
    @Query("SELECT a FROM Agendamento a WHERE a.dataHora >= :agora " + "AND a.hemocentro.id = :idHemocentro")
    List<Agendamento> findByIdUsuarioHemocentro_Id(
            @Param("agora") LocalDateTime agora,
            @Param("idHemocentro") Integer idHemocentro
    );
    Integer countByIdUsuarioHemocentro_IdAndDataHoraGreaterThanEqual(LocalDateTime agora, Integer idHemocentro);

    Integer countByIdUsuarioDoador_IdAndDataHoraGreaterThanEqual(LocalDateTime now, Integer idDoador);
}
