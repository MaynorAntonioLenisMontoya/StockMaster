package com.facturacion.stockmaster.business.Impl;

import co.com.brilla.sale.util.GenericBuild;
import com.facturacion.stockmaster.business.ProductoService;
import com.facturacion.stockmaster.dto.request.ProductoRequestDTO;
import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;
import com.facturacion.stockmaster.dto.response.InventaryProductReponseDTO;
import com.facturacion.stockmaster.dto.response.ProductsInventaryResponseDTO;
import com.facturacion.stockmaster.model.Producto;
import com.facturacion.stockmaster.repository.ProductoRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    private final Gson gson = new Gson();

    private final Utils utils;

    @Override
    public GenericBusinessResponseDTO create(ProductoRequestDTO request) {
        log.info("Inicia metodo create product");
        var producto = gson.fromJson(gson.toJson(request), Producto.class);
        producto = productoRepository.save(producto);
        var dto = gson.fromJson(gson.toJson(producto), ProductoRequestDTO.class);
        return utils.buildGenericBusinessResponse(dto, MessageUtils.CREATEDMESSAGE, 0L, null);
    }

    @Override
    public GenericBusinessResponseDTO getProducts(Integer pageSize, Integer pageNumber) {
        log.info("Inicia metodo getProducts paginator");
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        var products = productoRepository.findAll(pageable);
        return utils.buildGenericBusinessResponse(products.getContent(), MessageUtils.READMESSAGE, 0L, utils.buildPaginator(products));
    }

    @Override
    public GenericBusinessResponseDTO update(ProductoRequestDTO request) {
        log.info("Inicia metodo update product");
        var producto = gson.fromJson(gson.toJson(request), Producto.class);
        producto = productoRepository.save(producto);
        var dto = gson.fromJson(gson.toJson(producto), ProductoRequestDTO.class);
        return utils.buildGenericBusinessResponse(dto, MessageUtils.UPDATEMESSAGE, 0L, null);
    }

    @Override
    public GenericBusinessResponseDTO deleteById(Long id) {
        log.info("Inicia metodo delete product by id {}: ", id);
        productoRepository.deleteById(id);
        return utils.buildGenericBusinessResponse(null, MessageUtils.DELETEMESSAGE, 0L, null);
    }

    @Override
    public GenericBusinessResponseDTO findAllProduct() {
        log.info("Inicia metodo findAll products");
        return utils.buildGenericBusinessResponse(productoRepository.findAll(), MessageUtils.READMESSAGE, 0L, null);
    }

    @Override
    public GenericBusinessResponseDTO getInventaryProducts(Integer pageSize, Integer pageNumber) {
        log.info("Inicia metodo getInventaryProducts");
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Object[]> inventaryProducts = productoRepository.getInventaryProducts(pageable);

       var response = new GenericBuild<>(InventaryProductReponseDTO.class).buildList(inventaryProducts.getContent());

        return utils.buildGenericBusinessResponse(response, MessageUtils.READMESSAGE, 0L, utils.buildPaginator(inventaryProducts));
    }

    @Override
    public GenericBusinessResponseDTO getProductsByInventary(Long inventaryId, Integer pageSize, Integer pageNumber) {
        log.info("Inicia metodo getProductsByInventary");
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        var productInventary = productoRepository.getProductsInventaryById(inventaryId, pageable);

        var response = new GenericBuild<>(ProductsInventaryResponseDTO.class).buildList(productInventary.getContent());

        return utils.buildGenericBusinessResponse(response, MessageUtils.READMESSAGE, 0L, utils.buildPaginator(productInventary));
    }
}
