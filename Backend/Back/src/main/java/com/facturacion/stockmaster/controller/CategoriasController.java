package com.facturacion.stockmaster.controller;

import com.facturacion.stockmaster.business.CategoriaService;
import com.facturacion.stockmaster.dto.request.CategoriaRequestDTO;
import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${application.request.mappings}/v1")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class CategoriasController {

    private final CategoriaService categoriaService;

    @PostMapping("/create-category")
    public GenericBusinessResponseDTO create(@RequestBody CategoriaRequestDTO request) {
        log.info("Inicia controller create category");
        return categoriaService.create(request);
    }

    @GetMapping("/get-categories")
    public GenericBusinessResponseDTO getCategories(@RequestParam(value = "pageSize") Integer pageSize, @RequestParam(value = "pageNumber") Integer pageNumber) {
        log.info("Inicia controller getcategories");
        return categoriaService.getCategories(pageSize, pageNumber);
    }

    @PutMapping("/update-category")
    public GenericBusinessResponseDTO update(@RequestBody CategoriaRequestDTO request) {
        log.info("Inicia controller update category");
        return categoriaService.update(request);
    }

    @DeleteMapping("/delete/category/{id}")
    public GenericBusinessResponseDTO deleteById(@PathVariable Long id) {
        log.info("Inicia controller deleteById category");
        return categoriaService.deleteById(id);
    }

    @GetMapping("/get-all-categories")
    public GenericBusinessResponseDTO findAllCategory() {
        log.info("inicia controller findAllCategory");
        return categoriaService.findAllCategory();
    }
}
