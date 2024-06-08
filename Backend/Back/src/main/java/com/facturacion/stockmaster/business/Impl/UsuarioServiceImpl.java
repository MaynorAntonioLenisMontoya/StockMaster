package com.facturacion.stockmaster.business.Impl;

import com.facturacion.stockmaster.business.UsuarioService;
import com.facturacion.stockmaster.dto.request.UsuarioRequestDTO;
import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;
import com.facturacion.stockmaster.model.Usuario;
import com.facturacion.stockmaster.model.UsuarioRol;
import com.facturacion.stockmaster.repository.UsuarioRepository;
import com.facturacion.stockmaster.repository.UsuarioRolRepository;
import com.facturacion.stockmaster.util.MessageUtils;
import com.facturacion.stockmaster.util.PasswordEncoderUtils;
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
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioRolRepository usuarioRolRepository;

    private final Utils utils;

    private final Gson gson = new Gson();

    private final PasswordEncoderUtils passwordEncoderUtils;

    @Override
    public GenericBusinessResponseDTO create(UsuarioRequestDTO request) {
        log.info("Inicia metodo de crear usuarios reques enviado {} ", gson.toJson(request));
        var existUser = isUsedUsername(request.getUsername());

        if (existUser) {
            return utils.buildGenericBusinessResponse(null, "El Usuario ya existe", 1L, null);
        }

        String passwordEncoder = passwordEncoderUtils.encode(request.getContraseña());

        request.setContraseña(passwordEncoder);

        var usuario = gson.fromJson(gson.toJson(request), Usuario.class);
        log.info("Usuario a guardar {}: ", gson.toJson(usuario));

        usuarioRepository.save(usuario);

        var usuarioRol = buildUsuarioRol(usuario.getId(), request.getRolId());

        usuarioRol = usuarioRolRepository.save(usuarioRol);

        log.info("usuario_rol creado {}: ", gson.toJson(usuarioRol));

        var dto = gson.fromJson(gson.toJson(usuario), UsuarioRequestDTO.class);
        return utils.buildGenericBusinessResponse(dto, MessageUtils.CREATEDMESSAGE, 0L, null);
    }

    @Override
    public GenericBusinessResponseDTO getAllUser(Integer pageSize, Integer pageNumber) {
        log.info("Inicia metodo que lista los usuarios");

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        var usuarios = usuarioRepository.findAll(pageable);

        return utils.buildGenericBusinessResponse(usuarios.getContent(), MessageUtils.READMESSAGE, 0L, utils.buildPaginator(usuarios));
    }

    @Override
    public GenericBusinessResponseDTO deleteUserById(Long id) {
        log.info("Inicia metodo que elimina usuario");

        usuarioRepository.deleteById(id);

        return utils.buildGenericBusinessResponse(null, MessageUtils.DELETEMESSAGE, 0L, null);
    }

    private boolean isUsedUsername(String username) {
        return usuarioRepository.findByUsername(username).isPresent();
    }

    private UsuarioRol buildUsuarioRol(Long userId, Long rolId) {
        UsuarioRol usuarioRol = new UsuarioRol();

        usuarioRol.setUsuarioId(userId);
        usuarioRol.setRolId(rolId);

        return usuarioRol;
    }
}
