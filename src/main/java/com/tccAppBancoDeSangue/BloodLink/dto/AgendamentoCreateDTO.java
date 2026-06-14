package com.tccAppBancoDeSangue.BloodLink.dto;

import java.time.LocalDateTime;

public record AgendamentoCreateDTO(LocalDateTime data, Integer idUsuarioDoador, Integer idUsuarioHemocentro, Integer idCampanha) {

}
