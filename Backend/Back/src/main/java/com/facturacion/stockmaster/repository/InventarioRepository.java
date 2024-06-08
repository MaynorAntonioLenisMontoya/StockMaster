package com.facturacion.stockmaster.repository;

import com.facturacion.stockmaster.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}
