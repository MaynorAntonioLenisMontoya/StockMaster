package com.facturacion.stockmaster.business.Impl;

import com.facturacion.stockmaster.business.TipoTransicionService;
import com.facturacion.stockmaster.dto.request.TipoTransicionRequestDTO;
import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;
import com.facturacion.stockmaster.model.TipoTransicion;
import com.facturacion.stockmaster.repository.TipoTransicionRepository;
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
public class TipoTransicionServiceImpl implements TipoTransicionService {

    private final TipoTransicionRepository tipoTransicionRepository;
    private final Gson gson = new Gson();
    private final Utils utils;

    @Override
    public GenericBusinessResponseDTO create(TipoTransicionRequestDTO request) {
        log.info("Inicia metodo create");
        var tipoTransicion = gson.fromJson(gson.toJson(request), TipoTransicion.class);
        tipoTransicion = tipoTransicionRepository.save(tipoTransicion);
        var dto = gson.fromJson(gson.toJson(tipoTransicion), TipoTransicionRequestDTO.class);
        return utils.buildGenericBusinessResponse(dto, MessageUtils.CREATEDMESSAGE, 0L, null);
    }

    @Override
    public GenericBusinessResponseDTO getAllPage(Integer pageSize, Integer pageNumber) {
        log.info("Inicia metodo getAllPage");
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        var tipoTransicion = tipoTransicionRepository.findAll(pageable);
        return utils.buildGenericBusinessResponse(tipoTransicion.getContent(), MessageUtils.READMESSAGE, 0L, utils.buildPaginator(tipoTransicion));
    }

    @Override
    public GenericBusinessResponseDTO update(TipoTransicionRequestDTO request) {
        log.info("Inicia metodo update");
        var tipoTransicion = gson.fromJson(gson.toJson(request), TipoTransicion.class);
        tipoTransicion = tipoTransicionRepository.save(tipoTransicion);
        var dto = gson.fromJson(gson.toJson(tipoTransicion), TipoTransicionRequestDTO.class);
        return utils.buildGenericBusinessResponse(dto, MessageUtils.UPDATEMESSAGE, 0L, null);
    }

    @Override
    public GenericBusinessResponseDTO deleteById(Long id) {
        log.info("Inicia metodo deleteById");
        tipoTransicionRepository.deleteById(id);
        return utils.buildGenericBusinessResponse(null, MessageUtils.DELETEMESSAGE, 0L, null);
    }
}
