package com.autoexpres.service;

import com.autoexpres.model.Usuario;
import com.autoexpres.repository.UsuarioRepository;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UsuarioRepository usuarioRepository;

    public CustomOAuth2UserService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        try {
            // Cargar el usuario de Google por defecto
            OAuth2User oAuth2User = super.loadUser(userRequest);

            // Obtener el email del usuario de Google
            String email = oAuth2User.getAttribute("email");
            if (email == null) {
                throw new InternalAuthenticationServiceException("El email no se pudo recuperar de Google.");
            }

            // Buscar si el usuario ya existe en tu base de datos
            Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(email);

            Usuario usuario;
            if (usuarioExistente.isPresent()) {
                usuario = usuarioExistente.get();
                // Opcional: Actualizar datos si han cambiado (nombre, etc.)
                // usuario.setUsername(oAuth2User.getAttribute("name"));
                // usuario.setNombreCompleto(oAuth2User.getAttribute("name"));
            } else {
                // Si es un usuario nuevo, crear una nueva entrada en la BD
                usuario = new Usuario();
                usuario.setUsername(oAuth2User.getAttribute("name")); // Usar el nombre de Google como username
                usuario.setEmail(email);
                usuario.setPassword(""); // La contraseña no se usa para usuarios de Google
                usuario.setRoles("USER"); // Asignar un rol por defecto
                usuario.setActive(1);
            }

            usuarioRepository.save(usuario);

            // Retornar el usuario de OAuth2User con los roles que le asignaste
            return oAuth2User;

        } catch (OAuth2AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }
}