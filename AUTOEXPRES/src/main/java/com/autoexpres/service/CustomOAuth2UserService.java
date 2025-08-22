package com.autoexpres.service;

import com.autoexpres.model.Usuario;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;

    @Autowired
    public CustomOAuth2UserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // Carga la información del usuario por defecto
        OAuth2User oAuth2User = super.loadUser(userRequest);
        
        // Extrae el email y el nombre del usuario de la respuesta de Google
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // Busca si el usuario ya existe en tu base de datos por email
        Optional<Usuario> optionalUser = userService.findByEmail(email);
        Usuario usuario;
        
        if (optionalUser.isPresent()) {
            // El usuario ya existe, lo actualiza si es necesario
            usuario = optionalUser.get();
        } else {
            // Es un nuevo usuario, lo crea y lo guarda en la base de datos
            usuario = new Usuario();
            usuario.setEmail(email);
            
            // Asume que el nombre completo de Google es el nombre
            if (name != null) {
                // Divide el nombre en nombre y apellido
                String[] nameParts = name.split(" ", 2);
                usuario.setNombre(nameParts[0]);
                // Si hay un apellido, lo guarda, si no, lo deja vacío
                if (nameParts.length > 1) {
                    usuario.setApellido(nameParts[1]);
                } else {
                    usuario.setApellido("");
                }
            } else {
                usuario.setNombre(email); // O usa el email si el nombre no está disponible
                usuario.setApellido("");
            }
            
            // La contraseña es nula para usuarios de Google
            usuario.setPassword(null); 
            usuario.setRol("USER");
            userService.saveUser(usuario);
        }
        
        return oAuth2User;
    }
}
