package com.tccAppBancoDeSangue.BloodLink.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tccAppBancoDeSangue.BloodLink.model.TipoUsuario;
import com.tccAppBancoDeSangue.BloodLink.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    public Optional<Usuario> findByEmailAndSenha(String email, String senha);

    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByCnpj(String cnpj);
    List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);
}
