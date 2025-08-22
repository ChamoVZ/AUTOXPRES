CREATE DATABASE IF NOT EXISTS autoxpres_db;

USE autoxpres_db;



CREATE TABLE IF NOT EXISTS vehiculos_nuevos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    kilometraje INT DEFAULT 0,
    tipo_combustible VARCHAR(30),
    transmision VARCHAR(30),
    descripcion TEXT,
    ruta_imagen TEXT, 
    fecha_ingreso TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS vehiculos_alquiler (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    precio_por_dia DECIMAL(8, 2) NOT NULL,
    disponible BOOLEAN DEFAULT TRUE,
    descripcion TEXT,
    ruta_imagen TEXT, 
    fecha_adquisicion DATE
);
ALTER TABLE vehiculos_alquiler RENAME TO rentas_vehiculos;


CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL, -- Almacenar hash de la contraseña
    rol VARCHAR(50) NOT NULL,       -- Ej: 'ADMIN', 'VENDEDOR'
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL, -- Almacenar hash de la contraseña
    telefono VARCHAR(20),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS solicitudes_renta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    vehiculo_interes VARCHAR(100) NOT NULL,
    mensaje TEXT,
    fecha_solicitud TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
select*from solicitudes_renta;

CREATE TABLE IF NOT EXISTS cotizaciones_vehiculos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    vehiculo_interes VARCHAR(100) NOT NULL,
    mensaje TEXT,
    fecha_cotizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo_vehiculo ENUM('NUEVO', 'USADO') NOT NULL DEFAULT 'USADO'
);

SELECT*FROM cotizaciones_vehiculos;
DROP USER IF EXISTS 'AutoXpres'@'localhost';

-- Crear el usuario con la clave especificada
CREATE USER 'AutoXpres'@'localhost' IDENTIFIED BY 'Xpres';


GRANT ALL PRIVILEGES ON autoxpres_db.* TO 'AutoXpres'@'localhost';


FLUSH PRIVILEGES;

DESCRIBE vehiculos_nuevos;
SHOW TABLES;
ALTER TABLE vehiculos_nuevos
MODIFY COLUMN ruta_imagen TEXT NOT NULL;
