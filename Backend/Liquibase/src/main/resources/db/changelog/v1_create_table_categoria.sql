--liquibase formatted sql
--changeset Jhoan-stockmaster:1

CREATE TABLE categoria (
     id SERIAL PRIMARY KEY,
     nombre VARCHAR(255),
     descripcion VARCHAR(255)
);
