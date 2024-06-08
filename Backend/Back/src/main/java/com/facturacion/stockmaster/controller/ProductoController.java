package com.facturacion.stockmaster.controller;

import com.facturacion.stockmaster.business.ProductoService;
import com.facturacion.stockmaster.dto.request.ProductoRequestDTO;
import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${application.request.mappings}/v1")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping("/create-product")
    public GenericBusinessResponseDTO createProduct(@RequestBody ProductoRequestDTO request) {
        log.info("Inicia controller createProduct");
        return productoService.create(request);
    }

    @GetMapping("/get-inventary-products")
    public GenericBusinessResponseDTO getInventaryProducts(@RequestParam(value = "pageSize") Integer pageSize, @RequestParam(value = "pageNumber") Integer pageNumber) {
        log.info("Inicia controller getInventaryProducts");
        return productoService.getInventaryProducts(pageSize, pageNumber);
    }

    @GetMapping("/get-products/{inventaryId}")
    public GenericBusinessResponseDTO getProductsByInventary(@PathVariable Long inventaryId,
            @RequestParam(value = "pageSize") Integer pageSize, @RequestParam(value = "pageNumber") Integer pageNumber) {
        log.info("Inicia controller getProductsByInventary");
        return productoService.getProductsByInventary(inventaryId,pageSize, pageNumber);
    }


    @PutMapping("/update-product")
    public GenericBusinessResponseDTO updateProduct(@RequestBody ProductoRequestDTO request) {
        log.info("Inicia controller updateProduct");
        return productoService.update(request);
    }

    @DeleteMapping("/delete-product/{id}")
    public GenericBusinessResponseDTO deleteById(@PathVariable Long id) {
        log.info("Inicia controller deleteById");
        return productoService.deleteById(id);
    }

    @GetMapping("/get-all-products")
    public GenericBusinessResponseDTO findAll() {
        log.info("Inicia controller findAll products");
        return productoService.findAllProduct();
    }

}
