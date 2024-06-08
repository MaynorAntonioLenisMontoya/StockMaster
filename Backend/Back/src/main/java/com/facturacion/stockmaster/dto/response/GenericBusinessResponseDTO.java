package com.facturacion.stockmaster.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GenericBusinessResponseDTO {

    private Object result;

    private String message;

    private Long resultCode;

    private PaginatorDTO paginator;
}
