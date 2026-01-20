-- init.sql
CREATE DATABASE IF NOT EXISTS csu_db;
USE csu_db;
CREATE TABLE personas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dni BIGINT UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono BIGINT NOT NULL
) ENGINE=InnoDB;
CREATE TABLE usuarios (
    id BIGINT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    enabled BOOLEAN DEFAULT FALSE,
    rol ENUM('ADMIN', 'SALUD', 'VISITANTE', 'REFERENTE') NOT NULL,
    matricula BIGINT NULL,
    FOREIGN KEY (id) REFERENCES personas(id) ON DELETE CASCADE
) ENGINE=InnoDB;
-- 1. Tabla de Barrios (Requerida por Campania)
CREATE TABLE barrios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    geolocalizacion GEOMETRY NOT NULL SRID 4326,
    informacion_interes TEXT,
    SPATIAL INDEX(geolocalizacion)
) ENGINE=InnoDB;

-- 2. Tabla de Campañas
CREATE TABLE campanias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ruta_archivo_encuesta VARCHAR(512), -- Para la URL de Google Cloud / S3
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    barrio_id BIGINT NOT NULL,
    organizacion_social_id BIGINT,
    CONSTRAINT fk_campania_barrio FOREIGN KEY (barrio_id) REFERENCES barrios(id),
    CONSTRAINT fk_campania_org FOREIGN KEY (organizacion_social_id) REFERENCES organizaciones_sociales(id)
) ENGINE=InnoDB;

-- 3. Tabla intermedia para Encuestadores (el @ManyToMany de tu Java)
CREATE TABLE campania_encuestador (
    campania_id BIGINT NOT NULL,
    encuestador_id BIGINT NOT NULL,
    PRIMARY KEY (campania_id, encuestador_id),
    CONSTRAINT fk_ce_campania FOREIGN KEY (campania_id) REFERENCES campanias(id) ON DELETE CASCADE,
    CONSTRAINT fk_ce_encuestador FOREIGN KEY (encuestador_id) REFERENCES encuestadores(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- 4. Tabla de Jornadas (el @OneToMany de tu Java)
CREATE TABLE jornadas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    campania_id BIGINT NOT NULL,
    zona_id BIGINT NOT NULL, -- La zona específica dentro del barrio
    fecha DATE NOT NULL,
    CONSTRAINT fk_jornada_campania FOREIGN KEY (campania_id) REFERENCES campanias(id) ON DELETE CASCADE,
    CONSTRAINT fk_jornada_zona FOREIGN KEY (zona_id) REFERENCES zonas(id)
) ENGINE=InnoDB;
CREATE TABLE zonas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    barrio_id BIGINT NOT NULL, -- Siempre pertenece a un barrio
    nombre VARCHAR(100) NOT NULL, -- Ej: "Zona Norte", "Sector 1"
    limites POLYGON NOT NULL SRID 4326, -- Las fronteras de la zona
    CONSTRAINT fk_zona_barrio FOREIGN KEY (barrio_id) REFERENCES barrios(id) ON DELETE CASCADE,
    SPATIAL INDEX(limites) -- Para que las búsquedas en el mapa sean instantáneas
) ENGINE=InnoDB;