package com.tccAppBancoDeSangue.BloodLink.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public record ParticipanteCampanhaDTO(
        Integer idAgendamento,
        String nomeDoador,
        String nomeCampanha,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dataHora
) {}
