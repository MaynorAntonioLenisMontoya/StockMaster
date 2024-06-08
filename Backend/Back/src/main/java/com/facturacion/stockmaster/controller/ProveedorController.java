package com.facturacion.stockmaster.controller;

import com.facturacion.stockmaster.business.ProveedorService;
import com.facturacion.stockmaster.dto.request.ProveedorRequestDTO;
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
public class ProveedorController {

    private final ProveedorService proveedorService;


    @PostMapping("/create-proveedor")
    public GenericBusinessResponseDTO create(@RequestBody ProveedorRequestDTO request) {
        log.info("Inicia controller create-proveedor");
        return proveedorService.create(request);
    }

    @GetMapping("/get-provedores")
    public GenericBusinessResponseDTO getAllProveedores(@RequestParam(value = "pageSize") Integer pageSize, @RequestParam(value = "pageNumber") Integer pageNumber) {
        log.info("Inicia controller get-provedores");
        return proveedorService.getAllProveedorPageable(pageSize, pageNumber);
    }

    @PutMapping("/update-proveedor")
    public GenericBusinessResponseDTO update(@RequestBody ProveedorRequestDTO request) {
        log.info("Inicia controller update-proveedor");
        return proveedorService.update(request);
    }

    @DeleteMapping("/delete-proveedor/{id}")
    public GenericBusinessResponseDTO deleteById(@PathVariable Long id) {
        log.info("Inicia controller delete-proveedor");
        return proveedorService.deleteById(id);
    }

}
