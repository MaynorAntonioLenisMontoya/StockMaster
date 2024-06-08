package com.facturacion.stockmaster.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InventarioRequestDTO {

    private Long id;

    private String nombre;

    private String descripcion;
}
