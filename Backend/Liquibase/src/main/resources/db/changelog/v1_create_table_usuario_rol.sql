--liquibase formatted sql
--changeset Jhoan-stockmaster:1

CREATE TABLE usuario_rol (
    id SERIAL PRIMARY KEY,
    usuario_id INT,
    rol_id INT,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_rol FOREIGN KEY (rol_id) REFERENCES roles(id)
);
