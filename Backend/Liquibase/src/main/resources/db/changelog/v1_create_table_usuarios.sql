--liquibase formatted sql
--changeset Jhoan-stockmaster:1

CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nombres VARCHAR(255),
    apellidos VARCHAR(255),
    correo VARCHAR(255),
    contraseña VARCHAR(255)
);
