package com.tccAppBancoDeSangue.BloodLink.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tccAppBancoDeSangue.BloodLink.model.Campanha;
import com.tccAppBancoDeSangue.BloodLink.repository.CampanhaRepository;

@Service
public class CampanhaService {
    @Autowired
    private CampanhaRepository repository;

    public Integer contarCampanhasAtivas(Integer idHemo) {
        return repository.countByIdUsuarioHemocentro_IdAndDataFimGreaterThanEqual(idHemo, LocalDate.now());
    }

    public Campanha criarCampanha(Campanha campanha){
        return repository.save(campanha);
    }

    public Integer contarCampanhasFinalizadas(Integer idHemo) {
        return repository.countByIdUsuarioHemocentro_IdAndDataFimLessThan(idHemo, LocalDate.now());
    }

    public void deletarPorId(Integer idCampanha) {
        repository.deleteById(idCampanha);
    }

    public List<Campanha> buscarTodasPorId(Integer idUsuarioHemocentro) {
        return repository.findByIdUsuarioHemocentro_Id(idUsuarioHemocentro);
    }

    public Campanha buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Campanha não encontrada"));
    }

    public List<Campanha> buscarPorHemocentro(Integer idHemocentro){
    return repository.findByIdUsuarioHemocentro_Id(idHemocentro);
    }
}
