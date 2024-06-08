package com.facturacion.stockmaster.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductsInventaryResponseDTO {

    private Long id;

    private String nombre;

    private String descripcion;

    private String inventario;

    private String categoria;

    private Long cantidadDisponible;

    private Long nivelMinimoStock;

    private Long precioUnitario;
    
}
