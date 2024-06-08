package com.facturacion.stockmaster.business;

import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;

public interface UsuarioRolService {

    GenericBusinessResponseDTO create();

    GenericBusinessResponseDTO getAll(Integer pageSize, Integer pageNumber);

    GenericBusinessResponseDTO update();

    GenericBusinessResponseDTO deleteById(Long id);

}
