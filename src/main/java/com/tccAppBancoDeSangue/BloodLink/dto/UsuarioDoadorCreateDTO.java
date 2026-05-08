package com.tccAppBancoDeSangue.BloodLink.dto;

import com.tccAppBancoDeSangue.BloodLink.model.TipoSanguineo;
import com.tccAppBancoDeSangue.BloodLink.model.TipoUsuario;

import java.time.LocalDate;
import java.util.Date;

public record UsuarioDoadorCreateDTO(String nome, String email, TipoUsuario tipoUsuario, String senha, String cpf, LocalDate dataNasc, Double peso, TipoSanguineo tipoSanguineo) {

}
