package com.autoexpres.controller;

import com.autoexpres.model.Usuario;
import com.autoexpres.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerfilController {

    private final UserService userService;

    @Autowired
    public PerfilController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/perfil")
    public String showUserProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            
            // Si el usuario se autenticó con Google (OAuth2)
            if (authentication.getPrincipal() instanceof OAuth2User) {
                OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
                String email = oauth2User.getAttribute("email");
                String name = oauth2User.getAttribute("name");
                String picture = oauth2User.getAttribute("picture");

                model.addAttribute("nombre", name);
                model.addAttribute("email", email);
                model.addAttribute("foto", picture);
            
            // Si el usuario se autenticó con tu formulario de login
            } else if (authentication.getPrincipal() instanceof UserDetails) {
                String email = ((UserDetails) authentication.getPrincipal()).getUsername();
                Usuario user = userService.findByEmail(email).orElse(null);
                
                if (user != null) {
                    model.addAttribute("nombre", user.getNombre() + " " + user.getApellido());
                    model.addAttribute("email", user.getEmail());
                    // Asume una foto de perfil por defecto para usuarios con login normal
                    model.addAttribute("foto", "/images/default-profile.png"); 
                }
            }
            
            return "perfil";
        }
        
        // Si no hay usuario autenticado, redirige a la página de inicio
        return "redirect:/";
    }
}
