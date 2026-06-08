package com.tccAppBancoDeSangue.BloodLink.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tccAppBancoDeSangue.BloodLink.dto.LoginDTO;
import com.tccAppBancoDeSangue.BloodLink.dto.UsuarioDoadorCreateDTO;
import com.tccAppBancoDeSangue.BloodLink.dto.UsuarioHemocentroCreateDTO;
import com.tccAppBancoDeSangue.BloodLink.model.Usuario;
import com.tccAppBancoDeSangue.BloodLink.service.UsuarioService;

@RestController
@RequestMapping("/users")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @PostMapping("/salvarUsuarioDoador")
    public ResponseEntity<Void> salvarUserDoador(@RequestBody UsuarioDoadorCreateDTO dto){
        Usuario usuario = new Usuario();
        usuario.setCpf(dto.cpf());
        usuario.setDataNasc(dto.dataNasc());
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setTipoUsuario(dto.tipoUsuario());
        usuario.setPeso(dto.peso());
        usuario.setTipoSanguineo(dto.tipoSanguineo());
        usuario.setSenha(dto.senha());

        if(service.existePeloCpf(usuario.getCpf()) || service.existePeloEmail(usuario.getEmail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Não é possível cadastrar esses valores");
        }

        service.salvarUsuarioDoador(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/salvarUsuarioHemocentro")
    public ResponseEntity<Void> salvarUserHemocentro(@RequestBody UsuarioHemocentroCreateDTO dto){
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setCnpj(dto.cnpj());
        usuario.setSenha(dto.senha());
        usuario.setTipoUsuario(dto.tipoUsuario());
        usuario.setRua(dto.rua());
        usuario.setBairro(dto.bairro());
        usuario.setNumero(dto.numero());
        usuario.setCep(dto.cep());

        if(service.existePeloCnpj(usuario.getCnpj()) || service.existePeloEmail(usuario.getEmail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Não é possível cadastrar esses valores");
        }

        service.salvarUsuarioHemocentro(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> consultar(@RequestBody LoginDTO dto){
        Optional<Usuario> usuario = service.consultar(dto.email(), dto.senha());

        if(usuario.isPresent()){
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/buscarUsuarioPorId/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable Integer id){
        return service.buscarPorId(id);
    }
}
