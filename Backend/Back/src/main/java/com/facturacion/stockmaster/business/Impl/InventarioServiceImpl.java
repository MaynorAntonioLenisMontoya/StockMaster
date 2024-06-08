package com.facturacion.stockmaster.business.Impl;

import com.facturacion.stockmaster.business.InventarioService;
import com.facturacion.stockmaster.dto.request.InventarioRequestDTO;
import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;
import com.facturacion.stockmaster.model.Inventario;
import com.facturacion.stockmaster.repository.InventarioRepository;
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
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepository;

    private final Gson gson = new Gson();

    private final Utils utils;

    @Override
    public GenericBusinessResponseDTO create(InventarioRequestDTO request) {
        log.info("Inicia metodo create inventario request enviado {}: ", gson.toJson(request));
        var inventario = gson.fromJson(gson.toJson(request), Inventario.class);
        inventario = inventarioRepository.save(inventario);
        var dto = gson.fromJson(gson.toJson(inventario), InventarioRequestDTO.class);
        return utils.buildGenericBusinessResponse(dto, MessageUtils.CREATEDMESSAGE, 0L, null);
    }

    @Override
    public GenericBusinessResponseDTO getInventary(Integer pageSize, Integer pageNumber) {
        log.info("Inicia metodo getInventary pageSize {}:, pageNumber {}: ", pageSize, pageNumber);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        var inventaryPageList = inventarioRepository.findAll(pageable);
        log.info("inventaryPageList {}: ", inventaryPageList.getContent().size());
        return utils.buildGenericBusinessResponse(inventaryPageList.getContent(), MessageUtils.READMESSAGE, 0L, utils.buildPaginator(inventaryPageList));
    }

    @Override
    public GenericBusinessResponseDTO update(InventarioRequestDTO request) {
        log.info("Inicia metodo update inventario request enviado {}: ", gson.toJson(request));
        var inventario = gson.fromJson(gson.toJson(request), Inventario.class);
        inventario = inventarioRepository.save(inventario);
        var dto = gson.fromJson(gson.toJson(inventario), InventarioRequestDTO.class);
        return utils.buildGenericBusinessResponse(dto, MessageUtils.UPDATEMESSAGE, 0L, null);
    }

    @Override
    public GenericBusinessResponseDTO deleteById(Long id) {
        log.info("Inicia metodo deleteById id enviado {}: ", id);
        inventarioRepository.deleteById(id);
        return utils.buildGenericBusinessResponse(null, MessageUtils.UPDATEMESSAGE, 0L, null);
    }

    @Override
    public GenericBusinessResponseDTO findAllInventary() {
        log.info("Inicia metodo findAllInventary");
        return utils.buildGenericBusinessResponse(inventarioRepository.findAll(), MessageUtils.READMESSAGE, 0L, null);
    }
}
