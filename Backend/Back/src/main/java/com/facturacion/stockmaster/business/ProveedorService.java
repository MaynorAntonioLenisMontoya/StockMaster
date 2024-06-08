package com.facturacion.stockmaster.business;

import com.facturacion.stockmaster.dto.request.ProveedorRequestDTO;
import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;

public interface ProveedorService {

    GenericBusinessResponseDTO create(ProveedorRequestDTO request);

    GenericBusinessResponseDTO getAllProveedorPageable(Integer pageSize, Integer pageNumber);

    GenericBusinessResponseDTO update(ProveedorRequestDTO request);

    GenericBusinessResponseDTO deleteById(Long id);

}
