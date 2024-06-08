package com.facturacion.stockmaster.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductoRequestDTO {

    private Long id;

    private String nombre;

    private String descripcion;

    private Long categoriaId;

    private Long precioUnitario;

    private Long cantidadDisponible;

    private Long nivelMinimoStock;

    private Long inventarioId;
}
