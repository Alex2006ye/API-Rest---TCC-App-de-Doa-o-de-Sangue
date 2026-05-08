package com.tccAppBancoDeSangue.BloodLink.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Campanha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCampanha;
    private String nomeCampanha;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFim;
    @Enumerated(EnumType.STRING)
    private TipoSanguineo tipoSanguineoVisado;
    @ManyToOne //essas duas annotations ficam do lado que vai ter a chave estrangeira
    @JoinColumn(name = "idUsuarioHemocentro", nullable = false) //JPA criará uma chave estrangeira com isso
    @JsonIgnore
    private Usuario idUsuarioHemocentro;
}
