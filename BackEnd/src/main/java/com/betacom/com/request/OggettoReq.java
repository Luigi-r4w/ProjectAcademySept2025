package com.betacom.com.request;

import java.time.LocalDate;

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
public class OggettoReq {
	private Integer idOggetto;
	private Double prezzo;
    private String descrizione;  
    private String titolo; 
    private LocalDate dataCreazione;
    private String dimensione;
    private String autore;
    private String immagine;
    private Boolean isAI;
    private String categoria;
}
