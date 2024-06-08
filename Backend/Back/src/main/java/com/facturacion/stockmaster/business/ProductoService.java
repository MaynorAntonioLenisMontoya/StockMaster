package com.facturacion.stockmaster.business;

import com.facturacion.stockmaster.dto.request.ProductoRequestDTO;
import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;

public interface ProductoService {

    GenericBusinessResponseDTO create(ProductoRequestDTO request);

    GenericBusinessResponseDTO getProducts(Integer pageSize, Integer pageNumber);

    GenericBusinessResponseDTO update(ProductoRequestDTO request);

    GenericBusinessResponseDTO deleteById(Long id);

    GenericBusinessResponseDTO findAllProduct();

    GenericBusinessResponseDTO getInventaryProducts(Integer pageSize, Integer pageNumber);

    GenericBusinessResponseDTO getProductsByInventary(Long inventaryId,Integer pageSize, Integer pageNumber);

}
