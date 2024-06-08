package com.facturacion.stockmaster.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProveedorRequestDTO {

    private Long id;

    private String nombre;

    private String email;

    private String telefono;
}
