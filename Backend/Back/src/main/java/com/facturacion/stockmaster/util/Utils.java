package com.facturacion.stockmaster.util;

import com.facturacion.stockmaster.dto.response.GenericBusinessResponseDTO;
import com.facturacion.stockmaster.dto.response.PaginatorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class Utils<P> {

    public GenericBusinessResponseDTO buildGenericBusinessResponse(Object data, String message, Long code, PaginatorDTO paginator) {
        return GenericBusinessResponseDTO.builder()
                .result(data)
                .message(message)
                .resultCode(code)
                .paginator(paginator)
                .build();
    }

    public PaginatorDTO buildPaginator(Page<P> data) {
        return PaginatorDTO.builder()
                .pageNumber((long) data.getNumber())
                .pageSize((long) data.getSize())
                .totalItems(data.getTotalElements())
                .totalPages((long) data.getTotalPages())
                .hasNextPage(data.hasNext())
                .hasPreviousPage(data.hasPrevious())
                .nextPageNumber(data.hasNext() ? (long) data.getNumber() + 1 : null)
                .previousPageNumber(data.hasPrevious() ? (long) data.getNumber() - 1 : null)
                .build();
    }

}
