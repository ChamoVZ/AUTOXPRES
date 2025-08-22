package com.autoexpres.service;

import com.autoexpres.model.Usuario;
import com.autoexpres.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Método para guardar o actualizar un usuario en la base de datos
    public Usuario saveUser(Usuario usuario) {
        return userRepository.save(usuario);
    }

    // Método para buscar un usuario por su email
    public Optional<Usuario> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
