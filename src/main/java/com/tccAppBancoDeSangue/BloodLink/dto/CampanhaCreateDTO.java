package com.tccAppBancoDeSangue.BloodLink.dto;

import com.tccAppBancoDeSangue.BloodLink.model.TipoSanguineo;

import java.time.LocalDate;

public record CampanhaCreateDTO(String nomeCampanha, LocalDate dataInicio, LocalDate dataFim, TipoSanguineo tipoSanguineo, Integer idUsuarioHemocentro) {

}
