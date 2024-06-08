package com.facturacion.stockmaster.business;

import com.facturacion.stockmaster.dto.request.LoginRequestDTO;
import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;

public interface LoginService {

    GenericBusinessResponseDTO login(LoginRequestDTO request);

}
