package com.betacom.com.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class OggettoDTO {
    private Integer id;
    private String categoria;
	private String prezzo;
    private String descrizione;
    private String titolo;
    private LocalDate dataCreazione;
    private String dimensione;
    private String autore;
    private String immagine;
    private Boolean isAI;
}
