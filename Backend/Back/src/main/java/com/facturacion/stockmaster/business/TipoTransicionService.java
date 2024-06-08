package com.facturacion.stockmaster.business;

import com.facturacion.stockmaster.dto.request.TipoTransicionRequestDTO;
import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;

public interface TipoTransicionService {

    GenericBusinessResponseDTO create(TipoTransicionRequestDTO request);

    GenericBusinessResponseDTO getAllPage(Integer pageSize, Integer pageNumber);

    GenericBusinessResponseDTO update(TipoTransicionRequestDTO request);

    GenericBusinessResponseDTO deleteById(Long id);


}
