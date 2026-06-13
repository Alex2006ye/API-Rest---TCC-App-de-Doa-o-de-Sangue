package com.tccAppBancoDeSangue.BloodLink.dto;

import com.tccAppBancoDeSangue.BloodLink.model.TipoUsuario;

public record UsuarioHemocentroCreateDTO(String nome, String email, String senha, String cnpj, TipoUsuario tipoUsuario, String rua, String bairro, Integer numero, String cep) {

}
