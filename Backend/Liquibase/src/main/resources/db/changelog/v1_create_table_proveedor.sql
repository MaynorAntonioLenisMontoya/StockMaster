--liquibase formatted sql
--changeset Jhoan-stockmaster:1

CREATE TABLE proveedor (
     id SERIAL PRIMARY KEY,
     nombre VARCHAR(255),
     email VARCHAR(255),
    telefono VARCHAR(255)
);
