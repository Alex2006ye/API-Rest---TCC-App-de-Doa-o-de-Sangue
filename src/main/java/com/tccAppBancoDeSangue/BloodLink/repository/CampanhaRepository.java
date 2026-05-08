package com.tccAppBancoDeSangue.BloodLink.repository;

import com.tccAppBancoDeSangue.BloodLink.model.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CampanhaRepository extends JpaRepository<Campanha, Integer> {
    Integer countByIdUsuarioHemocentro_IdAndDataFimGreaterThanEqual(Integer id, LocalDate dataAtual);

    Integer countByIdUsuarioHemocentro_IdAndDataFimLessThan(Integer idHemo, LocalDate now);

    List<Campanha> findByIdUsuarioHemocentro_Id(Integer idUsuarioHemocentro);
}
