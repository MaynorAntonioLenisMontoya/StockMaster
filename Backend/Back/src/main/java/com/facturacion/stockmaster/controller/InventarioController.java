package com.facturacion.stockmaster.controller;

import com.facturacion.stockmaster.business.InventarioService;
import com.facturacion.stockmaster.dto.request.InventarioRequestDTO;
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
public class InventarioController {


    private final InventarioService inventarioService;

    @PostMapping("/create-inventary")
    public GenericBusinessResponseDTO create(@RequestBody InventarioRequestDTO request) {
        log.info("Inicia controller path /create-inventary");
        return inventarioService.create(request);
    }

    @GetMapping("/get-inventary")
    public GenericBusinessResponseDTO getInventary(@RequestParam(name = "pageSize") Integer pageSize, @RequestParam(name = "pageNumber") Integer pageNumber) {
        log.info("Inicia controller path get-inventary");
        return inventarioService.getInventary(pageSize, pageNumber);
    }

    @PutMapping("/update-inventary")
    public GenericBusinessResponseDTO updateInventary(@RequestBody InventarioRequestDTO request) {
        log.info("Inicia controller path update-inventary");
        return inventarioService.update(request);
    }

    @DeleteMapping("/delete-inventary/{id}")
    public GenericBusinessResponseDTO deleteInventary(@PathVariable Long id) {
        log.info("Inicia controller path delete-inventary");
        return inventarioService.deleteById(id);
    }

    @GetMapping("/get-all-inventary")
    public GenericBusinessResponseDTO findAllInventary() {
        log.info("Inicia controller findAllInventary");
        return inventarioService.findAllInventary();
    }

}
