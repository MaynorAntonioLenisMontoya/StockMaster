package com.facturacion.stockmaster.business.Impl;

import com.facturacion.stockmaster.business.CategoriaService;
import com.facturacion.stockmaster.dto.request.CategoriaRequestDTO;
import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;
import com.facturacion.stockmaster.model.Categoria;
import com.facturacion.stockmaster.repository.CategoriaReporitory;
import com.facturacion.stockmaster.util.MessageUtils;
import com.facturacion.stockmaster.util.Utils;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaReporitory categoriaReporitory;

    private final Gson gson = new Gson();

    private final Utils utils;

    @Override
    public GenericBusinessResponseDTO create(CategoriaRequestDTO request) {
        log.info("Inicia metodo create categoria request enviado {}: ", gson.toJson(request));
        var category = gson.fromJson(gson.toJson(request), Categoria.class);
        category = categoriaReporitory.save(category);
        var dto = gson.fromJson(gson.toJson(category), CategoriaRequestDTO.class);
        return utils.buildGenericBusinessResponse(dto, MessageUtils.CREATEDMESSAGE, 0L, null);
    }

    @Override
    public GenericBusinessResponseDTO getCategories(Integer pageSize, Integer pageNumber) {
        log.info("Inicia metodo getCategories pageSize {}:, pageNumber {}: ", pageSize, pageNumber);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        var categories = categoriaReporitory.findAll(pageable);

        return utils.buildGenericBusinessResponse(categories.getContent(), MessageUtils.READMESSAGE, 0L, utils.buildPaginator(categories));
    }

    @Override
    public GenericBusinessResponseDTO update(CategoriaRequestDTO request) {
        log.info("Inicia metodo create categoria request enviado {}: ", gson.toJson(request));
        var category = gson.fromJson(gson.toJson(request), Categoria.class);
        category = categoriaReporitory.save(category);
        var dto = gson.fromJson(gson.toJson(category), CategoriaRequestDTO.class);
        return utils.buildGenericBusinessResponse(dto, MessageUtils.UPDATEMESSAGE, 0L, null);
    }

    @Override
    public GenericBusinessResponseDTO deleteById(Long id) {
        log.info("Inicia metodo que elimina las categorias");
        categoriaReporitory.deleteById(id);
        return utils.buildGenericBusinessResponse(null, MessageUtils.DELETEMESSAGE, 0L, null);
    }

    @Override
    public GenericBusinessResponseDTO findAllCategory() {
        log.info("Inicia metodo findAllCategory");
        return utils.buildGenericBusinessResponse(categoriaReporitory.findAll(), MessageUtils.READMESSAGE, 0L,null);
    }
}
