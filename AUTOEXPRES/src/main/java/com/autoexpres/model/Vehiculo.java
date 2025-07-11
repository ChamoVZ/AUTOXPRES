package com.autoexpres.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import java.math.BigDecimal; // Para el precio

@Entity
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String anio; // Puedes usar String o Integer, String es más flexible para "2023.5" si fuera el caso

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String tipoCarroceria; // Ej. Sedán, SUV, Hatchback, Coupé

    @Column(nullable = false)
    private int kilometraje;

    @Column(nullable = false)
    private String transmision; // Ej. Automática, Manual

    @Column(nullable = false)
    private String tipoCombustible; // Ej. Gasolina, Diésel, Eléctrico, Híbrido

    @Column(nullable = false)
    private int numeroAsientos;

    @Column(nullable = false)
    private int numeroPuertas;

    @Column(nullable = false, precision = 10, scale = 2) // Precision total de 10 dígitos, 2 después del punto decimal
    private BigDecimal precio;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = true) // Puede ser nula si no hay imagen
    private String urlImagen; // URL o ruta a la imagen del vehículo

    // Constructores
    public Vehiculo() {
    }

    public Vehiculo(String marca, String modelo, String anio, String color, String tipoCarroceria, int kilometraje, String transmision, String tipoCombustible, int numeroAsientos, int numeroPuertas, BigDecimal precio, String descripcion, String urlImagen) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.color = color;
        this.tipoCarroceria = tipoCarroceria;
        this.kilometraje = kilometraje;
        this.transmision = transmision;
        this.tipoCombustible = tipoCombustible;
        this.numeroAsientos = numeroAsientos;
        this.numeroPuertas = numeroPuertas;
        this.precio = precio;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
    }

    // Getters y Setters (GENERAR CON TU IDE - Alt+Insert en IntelliJ, Source -> Generate Getters and Setters en Eclipse)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoCarroceria() {
        return tipoCarroceria;
    }

    public void setTipoCarroceria(String tipoCarroceria) {
        this.tipoCarroceria = tipoCarroceria;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public int getNumeroAsientos() {
        return numeroAsientos;
    }

    public void setNumeroAsientos(int numeroAsientos) {
        this.numeroAsientos = numeroAsientos;
    }

    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
               "id=" + id +
               ", marca='" + marca + '\'' +
               ", modelo='" + modelo + '\'' +
               ", anio='" + anio + '\'' +
               ", color='" + color + '\'' +
               ", tipoCarroceria='" + tipoCarroceria + '\'' +
               ", kilometraje=" + kilometraje +
               ", transmision='" + transmision + '\'' +
               ", tipoCombustible='" + tipoCombustible + '\'' +
               ", numeroAsientos=" + numeroAsientos +
               ", numeroPuertas=" + numeroPuertas +
               ", precio=" + precio +
               ", descripcion='" + descripcion + '\'' +
               ", urlImagen='" + urlImagen + '\'' +
               '}';
    }
}