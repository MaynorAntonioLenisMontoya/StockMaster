package com.facturacion.stockmaster.business;

import com.facturacion.stockmaster.dto.request.InventarioRequestDTO;
import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;

public interface InventarioService {

    GenericBusinessResponseDTO create(InventarioRequestDTO request);

    GenericBusinessResponseDTO getInventary(Integer pageSize, Integer pageNumber);

    GenericBusinessResponseDTO update(InventarioRequestDTO request);

    GenericBusinessResponseDTO deleteById(Long id);

    GenericBusinessResponseDTO findAllInventary();
}
