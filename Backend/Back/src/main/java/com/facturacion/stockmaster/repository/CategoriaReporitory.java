package com.facturacion.stockmaster.repository;

import com.facturacion.stockmaster.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaReporitory extends JpaRepository<Categoria, Long> {
}
