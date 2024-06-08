--liquibase formatted sql
--changeset Jhoan-stockmaster:1

CREATE TABLE productos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    descripcion VARCHAR(255),
    categoria_id INT,
    precio_unitario INT,
    cantidad_disponible INT,
    nivel_minimo_stock INT,
    CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);
