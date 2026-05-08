package com.tccAppBancoDeSangue.BloodLink.service;

import com.tccAppBancoDeSangue.BloodLink.model.Usuario;
import com.tccAppBancoDeSangue.BloodLink.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Usuario salvarUsuarioDoador(Usuario usuario){
        return repository.save(usuario);
    }

    public Usuario salvarUsuarioHemocentro(Usuario usuario){
        return repository.save(usuario);
    }

    public Optional<Usuario> consultar(String email, String senha){
        return repository.findByEmailAndSenha(email, senha);
    }

    public Usuario buscarPorId(Integer idUsuario){
        return repository.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public boolean existePeloEmail(String email){
        return repository.existsByEmail(email);
    }

    public boolean existePeloCpf(String cpf){
        return repository.existsByCpf(cpf);
    }

    public boolean existePeloCnpj(String cnpj){
        return repository.existsByCnpj(cnpj);
    }
}
