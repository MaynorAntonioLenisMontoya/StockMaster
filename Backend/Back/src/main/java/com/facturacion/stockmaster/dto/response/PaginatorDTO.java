package com.facturacion.stockmaster.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PaginatorDTO {

    private Long pageNumber;
    private Long pageSize;
    private Long totalItems;
    private Long totalPages;
    private Boolean hasNextPage;
    private Boolean hasPreviousPage;
    private Long nextPageNumber;
    private Long previousPageNumber;
}
