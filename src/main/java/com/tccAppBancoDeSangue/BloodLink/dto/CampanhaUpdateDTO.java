package com.tccAppBancoDeSangue.BloodLink.dto;

import com.tccAppBancoDeSangue.BloodLink.model.TipoSanguineo;

import java.time.LocalDate;

public record CampanhaUpdateDTO(Integer idCampanha, String nomeCampanha, LocalDate dataInicio, LocalDate dataFim, TipoSanguineo tipoSanguineoVisado) {

}
