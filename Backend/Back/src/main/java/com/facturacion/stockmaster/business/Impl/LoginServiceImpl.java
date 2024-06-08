package com.facturacion.stockmaster.business.Impl;

import com.facturacion.stockmaster.business.LoginService;
import com.facturacion.stockmaster.dto.UserDetails;
import com.facturacion.stockmaster.dto.request.LoginRequestDTO;
import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;
import com.facturacion.stockmaster.dto.response.LoginResponseDTO;
import com.facturacion.stockmaster.exeption.InvalidCredentialsException;
import com.facturacion.stockmaster.exeption.UserNotFoundException;
import com.facturacion.stockmaster.model.Rol;
import com.facturacion.stockmaster.model.Usuario;
import com.facturacion.stockmaster.model.UsuarioRol;
import com.facturacion.stockmaster.repository.RolRepository;
import com.facturacion.stockmaster.repository.UsuarioRepository;
import com.facturacion.stockmaster.repository.UsuarioRolRepository;
import com.facturacion.stockmaster.util.JwtUtil;
import com.facturacion.stockmaster.util.PasswordEncoderUtils;
import com.facturacion.stockmaster.util.Utils;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioRolRepository usuarioRolRepository;

    private final RolRepository rolRepository;

    private final Utils utils;

    private final Gson gson = new Gson();

    private final JwtUtil jwtUtil;

    private final PasswordEncoderUtils passwordEncoderUtils;

    @Override
    public GenericBusinessResponseDTO login(LoginRequestDTO request) {

        var user = getUserByUsername(request.getUsername());

        var passwordEncode = encryPasswordRequest(request.getPassword());

        if (!passwordEncode.equals(user.getContraseña())) {
            throw new InvalidCredentialsException("Usuario o contraseña incorrecta");
        }

        var usuarioRolList = getUsuarioRol(user.getId());

        var roles = buildListRoles(usuarioRolList);

        var userDetail = buildUserDetailt(user, roles);
        log.info("userDetail {}: ", gson.toJson(userDetail));

        var token = jwtUtil.generateToken(userDetail);

        var response = buildResponseObject(token);

        return utils.buildGenericBusinessResponse(response, "Sesion iniciada", 0L, null);
    }


    private Usuario getUserByUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("El usuario no existe"));
    }

    private String encryPasswordRequest(String password) {
        return passwordEncoderUtils.encode(password);
    }

    private List<UsuarioRol> getUsuarioRol(Long userId) {
        return usuarioRolRepository.findByUsuarioId(userId);
    }

    private UserDetails buildUserDetailt(Usuario usuario, List<String> roles) {
        return UserDetails.builder()
                .id(usuario.getId())
                .username(usuario.getUsername())
                .firstName(usuario.getNombres())
                .lastName(usuario.getApellidos())
                .email(usuario.getCorreo())
                .roles(roles)
                .build();
    }

    private List<String> buildListRoles(List<UsuarioRol> usuarioRols) {
        List<String> roles = new ArrayList<>();
        for (UsuarioRol usuarioRol: usuarioRols) {
            Rol rol = getRol(usuarioRol.getRolId());

            roles.add(rol.getNombre());

        }

        return roles;
    }

    private Rol getRol(Long rolId) {
        return rolRepository.findById(rolId)
                .orElseThrow(() -> new UserNotFoundException("El Rol no existe"));
    }

    private LoginResponseDTO buildResponseObject(String token) {
        return LoginResponseDTO.builder()
                .token(token)
                .build();
    }


}
