package com.tccAppBancoDeSangue.BloodLink.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tccAppBancoDeSangue.BloodLink.model.Campanha;

public interface CampanhaRepository extends JpaRepository<Campanha, Integer> {
    Integer countByIdUsuarioHemocentro_IdAndDataFimGreaterThanEqual(Integer id, LocalDate dataAtual);

    Integer countByIdUsuarioHemocentro_IdAndDataFimLessThan(Integer idHemo, LocalDate now);

    List<Campanha> findByIdUsuarioHemocentro_Id(Integer idUsuarioHemocentro);

}
