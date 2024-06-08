package com.facturacion.stockmaster.business;

import com.facturacion.stockmaster.dto.request.UsuarioRequestDTO;
import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;

public interface UsuarioService {

    GenericBusinessResponseDTO create(UsuarioRequestDTO request);

    GenericBusinessResponseDTO getAllUser(Integer pageSize, Integer pageNumber);

    GenericBusinessResponseDTO deleteUserById(Long id);

}
