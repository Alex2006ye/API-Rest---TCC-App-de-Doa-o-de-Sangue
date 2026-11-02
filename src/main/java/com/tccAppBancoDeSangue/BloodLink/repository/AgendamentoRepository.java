package com.tccAppBancoDeSangue.BloodLink.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tccAppBancoDeSangue.BloodLink.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    // basicamente, listar todos os agendamentos válidos pela data
    @Query("SELECT a FROM Agendamento a WHERE a.dataHora >= :agora " + "AND a.idUsuarioHemocentro.id = :idHemocentro")
    List<Agendamento> findByIdUsuarioHemocentro_Id(
            @Param("agora") LocalDateTime agora,
            @Param("idHemocentro") Integer idHemocentro
    );
    Integer countByIdUsuarioHemocentro_IdAndDataHoraGreaterThanEqual(LocalDateTime agora, Integer idHemocentro);

    Integer countByIdUsuarioDoador_IdAndDataHoraGreaterThanEqual(LocalDateTime now, Integer idDoador);

    @Query("SELECT a FROM Agendamento a WHERE a.dataHora >= :agora " + "AND a.idUsuarioDoador.id = :idDoador")
    List<Agendamento> findByIdUsuarioDoador_Id(@Param("agora") LocalDateTime agora, @Param("idDoador") Integer idDoador);

    Integer countByCampanha_IdCampanha(Integer idCampanha);

    boolean existsByIdUsuarioDoador_IdAndCampanha_IdCampanha(
        Integer idDoador, Integer idCampanha);

    @Query("""
    SELECT COUNT(a)
    FROM Agendamento a
    WHERE a.campanha.idUsuarioHemocentro.id = :idHemocentro
""")
Integer contarParticipantesCampanhas(
        @Param("idHemocentro") Integer idHemocentro
);
}
