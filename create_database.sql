-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS csu_app;

-- Crear el usuario (si no existe)
CREATE USER IF NOT EXISTS 'csu_dba'@'localhost' IDENTIFIED BY '321';

-- Dar todos los permisos sobre la base de datos correcta
GRANT ALL PRIVILEGES ON csu_app.* TO 'csu_dba'@'localhost';

-- Aplicar los cambios
FLUSH PRIVILEGES;