package com.autoexpres.config;

import com.autoexpres.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // Rutas públicas accesibles por cualquier persona
                .requestMatchers(
                    
                    "/", 
                    "/perfil",
                    "/catalogo",
                    "/registro",
                    "/alquiler",
                    "/nosotros",
                    "/contactenos",
                    "/renta",
                    "/cotizacion/porsche-911",
                    "/cotizacion/touareg-r",
                    "/cotizacion/bmw-m850i",
                    "/cotizacion/bmw-x6",
                    "/cotizacion/panamera",
                    "/cotizacion/audi-a6",
                    "vehiculos/Catalogo_Vehiculos",
                   //aqui empiezo lo de admin
                    "/vehiculo/{id}",
                    "/admin/vehiculos/detalle/{id}",
                    "/admin/vehiculos",
                    "/admin/vehiculos/nuevo",
                    "/admin/vehiculos/guardar",
                    "/admin/vehiculos/editar/{id}",
                    "/admin/vehiculos/eliminar/{id}",
                    "/admin/rentas",
                    "/admin/rentas/nuevo",
                    "/admin/rentas/guardar",
                    "/admin/rentas/editar/{id}",
                    "/admin/rentas/eliminar/{id}",
                    "/admin/rentas/detalle/{id}",
                    "admin/rentas/listaRentas",
                    
                    
                    "/renta/solicitar/{vehiculo}",
                    "/renta/solicitar",
                    "/admin/solicitudes",
                    "admin/rentas/FormRentaSolicitudes",
<<<<<<< HEAD
                    "admin/rentas/FormRentaSolicitudes",
                    "admin/rentas/ListaRentasSolicitudes",
=======
                    "admin/renta/FormRentaSolicitudes",
                    "admin/rentas/ListaRentaSolicitudes",
                    "/cotizacion/{vehiculo}",
                    "vehiculos/formCotizacion",
                    "/cotizacion/enviar",
                    "vehiculos/formCotizacion",
                    "/admin/cotizaciones",
                    "admin/cotizaciones/listaCotizaciones",
                    "/cotizacion/audi-a6",
                    "vehiculos/Audi_A6",
>>>>>>> 9373f8df271ec1e05ef910985e4d387b34b6c68f
                    //aqui termina lo e admin
                    "/detallevehiculo",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/vehiculos/detalle/**",
                    "/login" // Permite el acceso a la página de login
                ).permitAll()
                
                // Rutas protegidas que solo pueden ser accedidas por usuarios con rol "ADMIN"
                .requestMatchers("/admin/**").hasRole("ADMIN")
                
                // Cualquier otra solicitud requiere autenticación
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/login") 
                .userInfoEndpoint(userInfo -> userInfo
                    .userService(customOAuth2UserService)
                )
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}