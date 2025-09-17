package com.betacom.com.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class UtenteDTO {
    private Integer id;
    private String nome;
    private String email;
    private String password;
    private List<OggettoDTO> carrello;
    private String role;
}
