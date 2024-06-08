package com.facturacion.stockmaster.controller;

import com.facturacion.stockmaster.business.UsuarioService;
import com.facturacion.stockmaster.dto.request.UsuarioRequestDTO;
import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${application.request.mappings}/v1")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping("/create-user")
    public GenericBusinessResponseDTO create(@RequestBody UsuarioRequestDTO request) {
        return usuarioService.create(request);
    }

    @GetMapping("/get-users")
    public GenericBusinessResponseDTO getAllUsers(@RequestParam(value = "pageSize") Integer pageSize,  @RequestParam(value = "pageNumber") Integer pageNumber) {
        return usuarioService.getAllUser(pageSize, pageNumber);
    }

    @DeleteMapping("/delete-user/{id}")
    public GenericBusinessResponseDTO deleteById(@PathVariable Long id) {
        return usuarioService.deleteUserById(id);
    }

}
