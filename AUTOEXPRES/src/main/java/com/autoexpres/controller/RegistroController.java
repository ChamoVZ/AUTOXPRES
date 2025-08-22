package com.autoexpres.controller;

import com.autoexpres.model.Usuario;
import com.autoexpres.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistroController {

    private final UserService userService;

    @Autowired
    public RegistroController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Este método POST maneja el registro de un nuevo usuario.
     * Utiliza RedirectAttributes para enviar mensajes de éxito o error
     * a la página de destino después de la redirección.
     *
     * @param nombre El nombre del usuario.
     * @param apellido El apellido del usuario.
     * @param email El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @param redirectAttributes Utilizado para pasar mensajes temporales (flash attributes).
     * @return Una cadena que redirige a la página principal.
     */
    @PostMapping("/registro")
    public String procesarRegistro(
        @RequestParam("nombre") String nombre,
        @RequestParam("apellido") String apellido,
        @RequestParam("email") String email,
        @RequestParam("password") String password,
        RedirectAttributes redirectAttributes) {
        
        // Verifica si el correo electrónico ya existe en la base de datos
        if (userService.findByEmail(email).isPresent()) {
            // Si el correo ya está registrado, añade un mensaje de error flash
            redirectAttributes.addFlashAttribute("errorRegistro", "El correo electrónico ya está registrado.");
            return "redirect:/"; // Redirige a la página de inicio
        }
        
        // Crea una nueva instancia de usuario y asigna los datos del formulario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(password);
        nuevoUsuario.setRol("USER");
        
        // Guarda el nuevo usuario en la base de datos
        userService.saveUser(nuevoUsuario);
        
        // Si el registro es exitoso, añade un mensaje de éxito flash
        redirectAttributes.addFlashAttribute("exitoRegistro", "¡Registro exitoso! Ahora puedes iniciar sesión.");
        
        return "redirect:/"; // Redirige a la página de inicio
    }
}
