package com.facturacion.stockmaster.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InventaryProductReponseDTO {

    private Long inventaryId;

    private String nombre;

    private Long categoriaId;

    private String categoria;

    private Long cantidadProductos;
}
