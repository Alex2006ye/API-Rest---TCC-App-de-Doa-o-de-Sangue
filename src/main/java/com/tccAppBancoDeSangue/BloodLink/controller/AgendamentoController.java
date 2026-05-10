package com.tccAppBancoDeSangue.BloodLink.controller;

import com.tccAppBancoDeSangue.BloodLink.dto.AgendamentoCreateDTO;
import com.tccAppBancoDeSangue.BloodLink.model.Agendamento;
import com.tccAppBancoDeSangue.BloodLink.model.Usuario;
import com.tccAppBancoDeSangue.BloodLink.service.AgendamentoService;
import com.tccAppBancoDeSangue.BloodLink.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
    @Autowired
    private AgendamentoService service;

    @Autowired
    private UsuarioService serviceUser;

    @PostMapping("/criarAgendamento")
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody AgendamentoCreateDTO dto){
        Agendamento agendamento = new Agendamento();
        agendamento.setDataHora(dto.data());

        Usuario usuarioDoa = serviceUser.buscarPorId(dto.idUsuarioDoador());
        agendamento.setIdUsuarioDoador(usuarioDoa);

        Usuario usuarioHemo = serviceUser.buscarPorId(dto.idUsuarioHemocentro());
        agendamento.setIdUsuarioHemocentro(usuarioHemo);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarAgendamento(agendamento));
    }

    @GetMapping("/listarAgendamentos/{idHemo}")
    public List<Agendamento> listarTodosOsAgendamentosAtivos(@PathVariable Integer idUsuarioHemocentro){
        return service.buscarTodosPorId(idUsuarioHemocentro);
    }
}
