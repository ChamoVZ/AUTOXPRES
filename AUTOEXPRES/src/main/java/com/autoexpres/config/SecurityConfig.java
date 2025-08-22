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
                        "/cotizacion/audi-a6",
                    "vehiculos/Audi_A6",
                    "/cotizacion/bmw-m850i",
                    "vehiculos/BMW_Serie8",
                    "/cotizacion/bmw-x6",
                    "vehiculos/BMW_X6",
                    "/cotizacion/panamera",
                    "vehiculos/Porshe_Panamera",
                    "/cotizacion/touareg-r",
                    "vehiculos/Volkswagen_Touareg",
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
                        "admin/rentas/FormRentaSolicitudes",
                        "admin/rentas/ListaRentasSolicitudes",
                        "admin/renta/FormRentaSolicitudes",
                        "admin/rentas/ListaRentaSolicitudes",
                        "/cotizacion/{vehiculo}",
                        "/vehiculos/formCotizacion",
                        "/cotizacion/enviar",
                        "/admin/cotizaciones",
                        "admin/cotizaciones/listaCotizaciones",
                        "/cotizacion/audi-a6",
                        "vehiculos/Audi_A6",
                        //aqui termina lo e admin
                        "/detallevehiculo",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/vehiculos/detalle/**",
                        "/login"
                    
                      ).permitAll()
                // Rutas protegidas que solo pueden ser accedidas por usuarios con rol "ADMIN"
                .requestMatchers("/admin/**").hasRole("ADMIN")
                // Cualquier otra solicitud requiere autenticación
                .requestMatchers("/perfil").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                .loginPage("/")
                .defaultSuccessUrl("/", true)
                .permitAll()
                )
                .oauth2Login(oauth2Login -> oauth2Login
                .loginPage("/")
                .userInfoEndpoint(userInfo -> userInfo
                .userService(customOAuth2UserService)
                )
                )
                .logout(logout -> logout
                .logoutUrl("/logout")
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
