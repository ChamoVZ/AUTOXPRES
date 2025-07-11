package com.autoexpres.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table; 
import lombok.Data;              
import lombok.NoArgsConstructor;  
import lombok.AllArgsConstructor; 

import java.math.BigDecimal;    
import java.time.LocalDateTime; 

@Entity 
@Table(name = "vehiculos_nuevos") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Vehiculo {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable = false, length = 50) 
    private String marca;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(name = "anio", nullable = false) 
    private Integer anio; 

    @Column(nullable = false, precision = 10, scale = 2) 
    private BigDecimal precio;

    @Column(nullable = false)
    private Integer kilometraje; 

    @Column(name = "tipo_combustible", length = 30) 
    private String tipoCombustible;

    @Column(length = 30)
    private String transmision;

    @Column(columnDefinition = "TEXT") 
    private String descripcion;

    @Column(name = "ruta_imagen", nullable = false, length = 255) 
    private String rutaImagen; 

    @Column(name = "fecha_ingreso")
    private LocalDateTime fechaIngreso; 
}