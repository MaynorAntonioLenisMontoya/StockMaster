package com.facturacion.stockmaster.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TipoTransicionRequestDTO {

    private Long id;

    private String nombre;
}
