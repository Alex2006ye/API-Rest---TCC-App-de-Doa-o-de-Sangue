package com.tccAppBancoDeSangue.BloodLink.controller;

import com.tccAppBancoDeSangue.BloodLink.dto.CampanhaCreateDTO;
import com.tccAppBancoDeSangue.BloodLink.dto.CampanhaUpdateDTO;
import com.tccAppBancoDeSangue.BloodLink.model.Campanha;
import com.tccAppBancoDeSangue.BloodLink.model.Usuario;
import com.tccAppBancoDeSangue.BloodLink.service.CampanhaService;
import com.tccAppBancoDeSangue.BloodLink.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campanha")
public class CampanhaController {
    @Autowired
    private CampanhaService service;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/campanhasAtivas/{idHemo}")
    public Integer contarCampanhasAtivas(@PathVariable Integer idHemo){
        Integer totalCampanhasAtivas = service.contarCampanhasAtivas(idHemo);
        return totalCampanhasAtivas;
    }

    @GetMapping("/campanhasFinalizadas/{idHemo}")
    public Integer contarCampanhasFinalizadas(@PathVariable Integer idHemo){
        Integer totalCampanhasFinalizadas = service.contarCampanhasFinalizadas(idHemo);
        return totalCampanhasFinalizadas;
    }

    @PostMapping("/criarCampanha")
    public ResponseEntity<Campanha> criarCampanha(@RequestBody CampanhaCreateDTO dto){
        Campanha campanha = new Campanha();
        Usuario usuario = new Usuario();
        usuario = usuarioService.buscarPorId(dto.idUsuarioHemocentro());
        campanha.setNomeCampanha(dto.nomeCampanha());
        campanha.setDataInicio(dto.dataInicio());
        campanha.setDataFim(dto.dataFim());
        campanha.setTipoSanguineoVisado(dto.tipoSanguineo());
        campanha.setIdUsuarioHemocentro(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarCampanha(campanha));
    }

    @DeleteMapping("/deletar/{idCampanha}")
    public ResponseEntity<Void> deletarCampanha(@PathVariable Integer idCampanha){
        service.deletarPorId(idCampanha);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/alterarCampanha")
    public ResponseEntity<Void> alterarCampanha(@RequestBody CampanhaUpdateDTO dto){
        Campanha campanha = service.buscarPorId(dto.idCampanha());
        campanha.setIdCampanha(dto.idCampanha());
        campanha.setNomeCampanha(dto.nomeCampanha());
        campanha.setDataInicio(dto.dataInicio());
        campanha.setDataFim(dto.dataFim());
        campanha.setTipoSanguineoVisado(dto.tipoSanguineoVisado());

        service.criarCampanha(campanha);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/buscarTodos/{idUsuarioHemocentro}")
    public List<Campanha> buscarTodos(@PathVariable Integer idUsuarioHemocentro){
        return service.buscarTodasPorId(idUsuarioHemocentro);
    }
}
