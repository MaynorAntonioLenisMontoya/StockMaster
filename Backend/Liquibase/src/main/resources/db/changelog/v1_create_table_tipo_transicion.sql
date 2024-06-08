--liquibase formatted sql
--changeset Jhoan-stockmaster:1

CREATE TABLE tipo_transicion (
     id SERIAL PRIMARY KEY,
     nombre VARCHAR(255)
);
