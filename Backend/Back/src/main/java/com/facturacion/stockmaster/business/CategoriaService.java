package com.facturacion.stockmaster.business;

import com.facturacion.stockmaster.dto.request.CategoriaRequestDTO;
import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;

public interface CategoriaService {

    GenericBusinessResponseDTO create(CategoriaRequestDTO request);

    GenericBusinessResponseDTO getCategories(Integer pageSize, Integer pageNumber);

    GenericBusinessResponseDTO update(CategoriaRequestDTO request);

    GenericBusinessResponseDTO deleteById(Long id);

    GenericBusinessResponseDTO findAllCategory();
}
