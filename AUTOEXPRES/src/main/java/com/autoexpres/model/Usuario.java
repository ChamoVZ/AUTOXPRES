package com.autoexpres.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuarios")
@Data // Lombok para getters, setters, etc.
@NoArgsConstructor // Lombok para constructor sin argumentos
@AllArgsConstructor // Lombok para constructor con todos los argumentos
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre; // Coincide con el campo 'nombre' en tu DB

    @Column(nullable = false)
    private String apellido; // Coincide con el campo 'apellido' en tu DB

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true) // La contraseña puede ser nula si el usuario se registra con Google
    private String password;

    @Column(nullable = false)
    private String rol;

    // === Métodos de ayuda para roles ===
    @Transient // Indica a JPA que este campo no se mapea a una columna en la base de datos
    public List<String> getRoleList() {
        if (this.rol == null || this.rol.isBlank()) {
            return new ArrayList<>();
        }
        return Arrays.stream(this.rol.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

}