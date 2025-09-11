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
	private Integer id;
	private String prezzo;
    private String descrizione;  
    private String titolo; 
    private LocalDate dataCreazione;
    private String dimensione;
    private String autore;
    private String immagine;
    private String isAI;
}
