package com.facturacion.stockmaster.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginRequestDTO {

    private String username;

    private String password;
}
