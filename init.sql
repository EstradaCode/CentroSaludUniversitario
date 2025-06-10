-- init.sql
CREATE DATABASE IF NOT EXISTS csu_db;
USE csu_db;
CREATE TABLE Vec2D (
    id INT AUTO_INCREMENT PRIMARY KEY,
    x DOUBLE NOT NULL,
    y DOUBLE NOT NULL
);

CREATE TABLE Barrio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

CREATE TABLE Barrio_Coordenadas (
    barrio_id INT,
    vec2d_id INT,
    PRIMARY KEY (barrio_id, vec2d_id),
    FOREIGN KEY (barrio_id) REFERENCES Barrio(id),
    FOREIGN KEY (vec2d_id) REFERENCES Vec2D(id)
);

CREATE TABLE Persona (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    apellido VARCHAR(255),
    dni VARCHAR(50),
    telefono VARCHAR(50)
);

CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol ENUM('ADMIN', 'ENCUESTADOR', 'SUPERVISOR') NOT NULL,
    is_verified_user BOOLEAN DEFAULT FALSE,
    matricula BIGINT
);

CREATE TABLE Organizacion_Social (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    domicilio VARCHAR(255),
    barrio VARCHAR(255),
    actividad_principal VARCHAR(255)
);

CREATE TABLE Encuestador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    barrio_id INT,
    horas_trabajadas DOUBLE,
    FOREIGN KEY (barrio_id) REFERENCES Barrio(id)
);

CREATE TABLE Campania (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    archivo_encuestar VARCHAR(255),
    fecha_inicio DATE,
    fecha_finalizacion DATE
);

CREATE TABLE Campania_Zona (
    campania_id INT,
    barrio_id INT,
    PRIMARY KEY (campania_id, barrio_id),
    FOREIGN KEY (campania_id) REFERENCES Campania(id),
    FOREIGN KEY (barrio_id) REFERENCES Barrio(id)
);

CREATE TABLE Campania_Encuestador (
    campania_id INT,
    encuestador_id INT,
    PRIMARY KEY (campania_id, encuestador_id),
    FOREIGN KEY (campania_id) REFERENCES Campania(id),
    FOREIGN KEY (encuestador_id) REFERENCES Encuestador(id)
);

CREATE TABLE Jornada (
    id INT AUTO_INCREMENT PRIMARY KEY,
    horario_inicio TIME,
    horario_final TIME,
    fecha DATE
);

CREATE TABLE Encuesta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    jornada_id INT,
    FOREIGN KEY (jornada_id) REFERENCES Jornada(id)
);

CREATE TABLE Filtro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    valor_seleccionado VARCHAR(255),
    operacion VARCHAR(50),
    is_active BOOLEAN
);

