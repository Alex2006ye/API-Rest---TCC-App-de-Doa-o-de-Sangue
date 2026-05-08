package com.tccAppBancoDeSangue.BloodLink.repository;

import com.tccAppBancoDeSangue.BloodLink.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    public Optional<Usuario> findByEmailAndSenha(String email, String senha);

    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByCnpj(String cnpj);
}
