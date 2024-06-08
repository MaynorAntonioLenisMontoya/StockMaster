package com.facturacion.stockmaster.business.Impl;

import com.facturacion.stockmaster.business.ProveedorService;
import com.facturacion.stockmaster.dto.request.ProveedorRequestDTO;
import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;
import com.facturacion.stockmaster.model.Proveedor;
import com.facturacion.stockmaster.repository.ProveedorRepository;
import com.facturacion.stockmaster.util.MessageUtils;
import com.facturacion.stockmaster.util.Utils;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    private final Gson gson = new Gson();

    private final Utils utils;


    @Override
    public GenericBusinessResponseDTO create(ProveedorRequestDTO request) {
        log.info("Inicia metodo que crea proveedores {}: ", gson.toJson(request));

        var proveedor = gson.fromJson(gson.toJson(request), Proveedor.class);

        proveedor = proveedorRepository.save(proveedor);

        var dto = gson.fromJson(gson.toJson(proveedor), ProveedorRequestDTO.class);

        return utils.buildGenericBusinessResponse(dto, MessageUtils.CREATEDMESSAGE, 0L, null);
    }

    @Override
    public GenericBusinessResponseDTO getAllProveedorPageable(Integer pageSize, Integer pageNumber) {
        log.info("Inicia metodo que lista proveedores");

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        var proveedores = proveedorRepository.findAll(pageable);

        return utils.buildGenericBusinessResponse(proveedores.getContent(), MessageUtils.READMESSAGE, 0L, utils.buildPaginator(proveedores));
    }

    @Override
    public GenericBusinessResponseDTO update(ProveedorRequestDTO request) {
        log.info("Inicia metodo que actualiza proveedores {}: ", gson.toJson(request));

        var proveedor = gson.fromJson(gson.toJson(request), Proveedor.class);

        proveedor = proveedorRepository.save(proveedor);

        var dto = gson.fromJson(gson.toJson(proveedor), ProveedorRequestDTO.class);

        return utils.buildGenericBusinessResponse(dto, MessageUtils.UPDATEMESSAGE, 0L, null);
    }

    @Override
    public GenericBusinessResponseDTO deleteById(Long id) {
        log.info("Inicia metodo que borra proveedores id {}: ", id);

        proveedorRepository.deleteById(id);

        return utils.buildGenericBusinessResponse(null, MessageUtils.DELETEMESSAGE, 0L, null);
    }
}
