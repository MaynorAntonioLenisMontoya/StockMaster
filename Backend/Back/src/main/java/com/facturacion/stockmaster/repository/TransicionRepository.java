package com.facturacion.stockmaster.repository;

import com.facturacion.stockmaster.model.Transicion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransicionRepository extends JpaRepository<Transicion, Long> {
}
