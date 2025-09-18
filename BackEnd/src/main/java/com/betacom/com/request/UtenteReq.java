package com.betacom.com.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UtenteReq {
    private Integer id;
    private String nome;
    private String email;
    private String password;
    private String role;
}
