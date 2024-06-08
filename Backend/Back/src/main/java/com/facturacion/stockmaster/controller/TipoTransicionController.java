package com.facturacion.stockmaster.controller;

import com.facturacion.stockmaster.business.TipoTransicionService;
import com.facturacion.stockmaster.dto.request.TipoTransicionRequestDTO;
import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${application.request.mappings}/v1")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class TipoTransicionController {

    private final TipoTransicionService tipoTransicionService;

    @PostMapping("/create-tipo-transicion")
    public GenericBusinessResponseDTO create(@RequestBody TipoTransicionRequestDTO request) {
        log.info("Inicia controller create /create-tipo-transicion");
        return tipoTransicionService.create(request);
    }

    @GetMapping("/get-tipo-transicion")
    public GenericBusinessResponseDTO getAll(@RequestParam(value = "pageSize") Integer pageSize, @RequestParam(value = "pageNumber") Integer pageNumber) {
        log.info("Inicia controller getAll /get-tipo-transicion");
        return tipoTransicionService.getAllPage(pageSize, pageNumber);
    }

    @PutMapping("/update-tipo-transicion")
    public GenericBusinessResponseDTO update(@RequestBody TipoTransicionRequestDTO request) {
        log.info("Inicia controller update /update-tipo-transicion");
        return tipoTransicionService.update(request);
    }

    @DeleteMapping("/delete-tipo-transicion/{id}")
    public GenericBusinessResponseDTO deleteById(@PathVariable Long id) {
        log.info("Inicia controller deleteById /delete-tipo-transicion/{id}");
        return tipoTransicionService.deleteById(id);
    }

}
