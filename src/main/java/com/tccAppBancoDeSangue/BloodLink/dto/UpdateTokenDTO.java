package com.tccAppBancoDeSangue.BloodLink.dto;

public record UpdateTokenDTO (
    Integer idUsuario,
    String tokenFcm
) {}
