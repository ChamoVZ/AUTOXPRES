package com.autoexpres.repository;

import com.autoexpres.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    // Método para buscar un usuario por su email.
    // Necesario tanto para el registro como para el login por formulario y OAuth2.
    Optional<Usuario> findByEmail(String email);
}
