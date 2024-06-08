package com.facturacion.stockmaster.repository;

import com.facturacion.stockmaster.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
}
