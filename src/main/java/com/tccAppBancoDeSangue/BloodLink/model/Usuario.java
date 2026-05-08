package com.tccAppBancoDeSangue.BloodLink.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;
    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String cnpj;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNasc;
    private Double peso;
    private String rua;
    private String cep;
    @Enumerated(EnumType.STRING)
    private TipoSanguineo tipoSanguineo;
    private Integer numero;
    private String bairro;
}
