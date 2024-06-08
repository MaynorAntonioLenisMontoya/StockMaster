package com.facturacion.stockmaster.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UsuarioRequestDTO {

    private Long id;

    private String nombres;

    private String apellidos;

    private String correo;

    private String contraseña;

    private String username;

    private Long rolId;

}
