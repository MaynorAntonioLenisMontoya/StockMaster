package com.facturacion.stockmaster.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDetails {

    private Long id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private List<String> roles;


}
