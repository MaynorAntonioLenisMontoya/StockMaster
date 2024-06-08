package com.facturacion.stockmaster.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginResponseDTO {

    private String token;
}
