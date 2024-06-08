package com.facturacion.stockmaster.repository;

import com.facturacion.stockmaster.model.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long> {

    List<UsuarioRol> findByUsuarioId(Long userId);

}
