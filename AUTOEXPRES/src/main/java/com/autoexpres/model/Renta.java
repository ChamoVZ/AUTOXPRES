package com.autoexpres.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "rentas_vehiculos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Renta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campos existentes en la tabla
    @Column(nullable = false, length = 50)
    private String marca;
    
    @Column(nullable = false, length = 50)
    private String modelo;
    
    @Column(name = "anio", nullable = false)
    private Integer anio;
    
    @Column(name = "precio_por_dia", nullable = false, precision = 8, scale = 2)
    private BigDecimal precioPorDia;
    
    @Column(nullable = false)
    private Boolean disponible = true;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "ruta_imagen")
    private String rutaImagen;
    
    @Column(name = "fecha_adquisicion")
    private LocalDate fechaAdquisicion;

    // Campos transitorios para la renta (NO se guardan en BD)
    @Transient
    private String clienteNombre;
    
    @Transient
    private LocalDate fechaInicio;
    
    @Transient
    private LocalDate fechaFin;
    
    @Transient
    private BigDecimal costoTotal; // Este campo será digitado manualmente
}