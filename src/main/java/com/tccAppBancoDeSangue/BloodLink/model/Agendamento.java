package com.tccAppBancoDeSangue.BloodLink.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgendamento;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") //deixar o atributo nesse formato
    private LocalDateTime dataHora;
    @ManyToOne //essas duas annotations ficam do lado que vai ter a chave estrangeira
    @JoinColumn(name = "idUsuarioHemocentro", nullable = false) //JPA criará uma chave estrangeira com isso
    @JsonIgnore
    private Usuario idUsuarioHemocentro;
    @ManyToOne //essas duas annotations ficam do lado que vai ter a chave estrangeira
    @JoinColumn(name = "idUsuarioDoador", nullable = false) //JPA criará uma chave estrangeira com isso
    @JsonIgnore
    private Usuario idUsuarioDoador;
    @ManyToOne
    @JoinColumn(name = "idCampanha")
    @JsonIgnore
    private Campanha campanha;
}
