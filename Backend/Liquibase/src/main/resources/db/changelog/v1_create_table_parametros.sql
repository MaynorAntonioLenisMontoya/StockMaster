--liquibase formatted sql
--changeset Jhoan-stockmaster:1

CREATE TABLE parametros (
     id SERIAL PRIMARY KEY,
     nombre VARCHAR(255),
     descripcion VARCHAR(255),
     valor VARCHAR(255)
);
