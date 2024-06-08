--liquibase formatted sql
--changeset Jhoan-stockmaster:1

ALTER TABLE usuarios
    ADD COLUMN username VARCHAR(255);