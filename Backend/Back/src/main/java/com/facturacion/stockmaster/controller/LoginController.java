package com.facturacion.stockmaster.controller;

import com.facturacion.stockmaster.business.LoginService;
import com.facturacion.stockmaster.dto.request.LoginRequestDTO;
import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${application.request.mappings}/v1")
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@CrossOrigin(origins = "*")
public class LoginController {

    private final LoginService loginService;


    @PostMapping("/login")
    public GenericBusinessResponseDTO login(@RequestBody LoginRequestDTO request) {
        return loginService.login(request);
    }

}
