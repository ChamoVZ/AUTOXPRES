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

    @NotBlank
    @Column(nullable = false, unique = true)
    private String username; // Usaremos esto para el login

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    // Aquí se guardarán los roles, separados por comas (ej. "ADMIN,USER")
    @Column(nullable = false)
    private String roles = "";

    // Aquí se guardarán los permisos, separados por comas (ej. "CREATE,DELETE")
    @Column(nullable = false)
    private String permissions = "";

    @Column(nullable = false)
    private int active = 1;

    // === Métodos de ayuda para roles y permisos ===
    
    @Transient // Indica a JPA que este campo no se mapea a una columna en la base de datos
    public boolean isEnabled() {
        return this.active == 1;
    }
    
    @Transient
    public List<String> getRoleList() {
        if (this.roles == null || this.roles.isBlank()) {
            return new ArrayList<>();
        }
        return Arrays.stream(this.roles.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    @Transient
    public List<String> getPermissionList() {
        if (this.permissions == null || this.permissions.isBlank()) {
            return new ArrayList<>();
        }
        return Arrays.stream(this.permissions.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}