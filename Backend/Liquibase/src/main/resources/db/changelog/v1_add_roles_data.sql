--liquibase formatted sql
--changeset Jhoan-stockmaster:1

insert into roles (nombre, descripcion)
values('ADMIN', 'ROl Administrador');

insert into roles (nombre, descripcion)
values('INVENTARIO', 'ROl que administra inventario');