--liquibase formatted sql
--changeset Jhoan-stockmaster:1

CREATE TABLE transicion (
    id SERIAL PRIMARY KEY,
    usuario_id INT,
    producto_id INT,
    proveedor_id INT,
    tipo_transicion_id INT,
    cantidad INT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    notas TEXT,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_producto FOREIGN KEY (producto_id) REFERENCES productos(id),
    CONSTRAINT fk_proveedor FOREIGN KEY (proveedor_id) REFERENCES proveedor(id),
    CONSTRAINT fk_tipo_transicion FOREIGN KEY (tipo_transicion_id) REFERENCES tipo_transicion(id)
);
