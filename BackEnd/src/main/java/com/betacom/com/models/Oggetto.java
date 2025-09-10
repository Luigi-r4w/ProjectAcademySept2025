package com.betacom.com.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "oggetti")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Oggetto {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "prezzo")
    private String prezzo;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "titolo")
    private String titolo;
    
    @Column(name = "data_creazione")
    private LocalDate dataCreazione;

    @Column(name = "dimensione")
    private String dimensione;
    
    @Column(name = "autore")
    private String autore;

    @Column(name = "immagine")
    private String immagine;
    
    @Column(name = "is_ai")
    private String isAI;
    
}
