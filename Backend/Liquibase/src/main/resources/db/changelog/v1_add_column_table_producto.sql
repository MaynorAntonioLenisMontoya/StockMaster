--liquibase formatted sql
--changeset Jhoan-stockmaster:1

ALTER TABLE productos
    ADD COLUMN inventario_id INT,
    ADD CONSTRAINT fk_inventario FOREIGN KEY (inventario_id) REFERENCES inventario(id)