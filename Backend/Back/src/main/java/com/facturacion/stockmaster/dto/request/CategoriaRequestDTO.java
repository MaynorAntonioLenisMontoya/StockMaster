package com.facturacion.stockmaster.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CategoriaRequestDTO {

    private Long id;

    private String nombre;

    private String descripcion;
}
