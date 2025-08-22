package com.autoexpres.controller;

import com.autoexpres.model.Usuario;
import com.autoexpres.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistroController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistroController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registro")
    public String procesarRegistro(
        @RequestParam("nombre") String nombre,
        @RequestParam("apellido") String apellido,
        @RequestParam("email") String email,
        @RequestParam("password") String password,
        RedirectAttributes redirectAttributes) {

        try {
            if (userService.findByEmail(email).isPresent()) {
                redirectAttributes.addFlashAttribute("registroError", "El correo electrónico ya está registrado.");
                return "redirect:/login";
            }

            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setApellido(apellido);
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setPassword(passwordEncoder.encode(password));
            nuevoUsuario.setRol("USER");

            userService.saveUser(nuevoUsuario);

            redirectAttributes.addFlashAttribute("registroExitoso", "¡Cuenta creada con éxito! Por favor, inicia sesión.");
            return "redirect:/login";
        } catch (Exception e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            redirectAttributes.addFlashAttribute("registroError", "Hubo un error al crear la cuenta. Por favor, inténtalo de nuevo.");
            return "redirect:/login";
        }
    }
}
