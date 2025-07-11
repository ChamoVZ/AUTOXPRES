package com.autoexpres.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table; // Importación necesaria para @Table
import lombok.Data;              // Importación de Lombok
import lombok.NoArgsConstructor;  // Importación de Lombok para constructor sin argumentos
import lombok.AllArgsConstructor; // Importación de Lombok para constructor con todos los argumentos

import java.math.BigDecimal;    // Para el tipo de dato DECIMAL en el precio
import java.time.LocalDateTime; // Para el tipo de dato TIMESTAMP en fecha_ingreso

@Entity // Indica que esta clase es una entidad JPA y se mapeará a una tabla de base de datos
@Table(name = "vehiculos_nuevos") // Especifica el nombre de la tabla en la base de datos
@Data // Anotación de Lombok: Genera automáticamente getters, setters, toString(), equals(), y hashCode()
@NoArgsConstructor // Anotación de Lombok: Genera un constructor sin argumentos (necesario para JPA)
@AllArgsConstructor // Anotación de Lombok: Genera un constructor con todos los argumentos
public class Vehiculo {

    @Id // Marca esta propiedad como la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura la estrategia de generación de ID (auto-incremento para MySQL)
    private Long id;

    @Column(nullable = false, length = 50) // 'nullable = false' significa que la columna no puede ser nula. 'length' es opcional pero bueno para VARCHAR.
    private String marca;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(name = "anio", nullable = false) // Mapea la propiedad 'anio' de Java a la columna 'anio' en la DB
    private Integer anio; // Se usa Integer para el año, que es más apropiado que String para datos numéricos.

    @Column(nullable = false, precision = 10, scale = 2) // 'precision' es el número total de dígitos, 'scale' es el número de dígitos después del punto decimal.
    private BigDecimal precio;

    @Column(nullable = false)
    private Integer kilometraje; // Se usa Integer para el kilometraje.

    @Column(name = "tipo_combustible", length = 30) // Mapea 'tipoCombustible' de Java a 'tipo_combustible' en la DB
    private String tipoCombustible;

    @Column(length = 30)
    private String transmision;

    @Column(columnDefinition = "TEXT") // Usa 'columnDefinition' para tipos de texto largos
    private String descripcion;

    @Column(name = "ruta_imagen", nullable = false, length = 255) // Mapea 'rutaImagen' de Java a 'ruta_imagen' en la DB
    private String rutaImagen; // Este es el campo que usarás en tu HTML con th:src="${vehiculo.rutaImagen}"

    @Column(name = "fecha_ingreso")
    private LocalDateTime fechaIngreso; // Para la columna TIMESTAMP en tu DB
}