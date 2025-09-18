package com.betacom.com.utils;

import java.time.LocalDate;

import com.betacom.com.dto.OggettoDTO;
import com.betacom.com.exception.AcademyException;
import com.betacom.com.models.Oggetto;
import com.betacom.com.request.OggettoReq;

public class Utilities {
	
	public static <R extends OggettoReq> void verificaOggetto(R req) throws AcademyException{
		
		if (req.getCategoria() == null)
		    throw new AcademyException("Categoria obbligatoria");
		
		if (req.getPrezzo() == null)
		    throw new AcademyException("Prezzo obbligatorio");

		if (req.getDescrizione() == null || req.getDescrizione().isBlank()) 
		    throw new AcademyException("Descrizione obbligatoria");

		if (req.getTitolo() == null || req.getTitolo().isBlank()) 
		    throw new AcademyException("Titolo obbligatorio");

		if (req.getDimensione() == null || req.getDimensione().isBlank())
		    throw new AcademyException("Dimensione obbligatoria");

		if (req.getAutore() == null || req.getAutore().isBlank())
		    throw new AcademyException("Autore obbligatorio");

		if (req.getImmagine() == null || req.getImmagine().isBlank())
		    throw new AcademyException("Immagine obbligatoria");

		if (req.getIsAI() == null)
		    throw new AcademyException("Campo 'isAI' obbligatorio");
		
	}
	
	public static <T extends Oggetto, R extends OggettoReq> T riempiOggetto(T oggetto, R req) {
		
		oggetto.setCategoria(req.getCategoria());
		oggetto.setPrezzo(req.getPrezzo());
		oggetto.setDescrizione(req.getDescrizione());
		oggetto.setTitolo(req.getTitolo());
		oggetto.setDataCreazione(req.getDataCreazione() == null ? LocalDate.now() : req.getDataCreazione());
		oggetto.setDimensione(req.getDimensione());
		oggetto.setAutore(req.getAutore());
		oggetto.setImmagine(req.getImmagine());
		oggetto.setIsAI(req.getIsAI());
		
		return oggetto;
	}
	
	public static <T extends Oggetto, R extends OggettoReq> T modificaOggetto(T oggetto, R req) {

		if (req.getCategoria() != null && !req.getCategoria().isBlank())
			oggetto.setCategoria(req.getCategoria());
		if (req.getPrezzo() != null)
			oggetto.setPrezzo(req.getPrezzo());
		if (req.getDescrizione()!= null && !req.getDescrizione().isBlank())
			oggetto.setDescrizione(req.getDescrizione());
		if (req.getTitolo()!= null && !req.getTitolo().isBlank())
			oggetto.setTitolo(req.getTitolo());
		if (req.getDataCreazione() != null)
			oggetto.setDataCreazione(req.getDataCreazione());
		if (req.getDimensione() != null && !req.getDimensione().isBlank())
			oggetto.setDimensione(req.getDimensione());
		if (req.getAutore() != null && !req.getAutore().isBlank())
			oggetto.setAutore(req.getAutore());
		if (req.getImmagine() != null)
			oggetto.setImmagine(req.getImmagine());
		if (req.getIsAI() != null)
			oggetto.setIsAI(req.getIsAI());
		
		return oggetto;
	}
	
	public static <T extends Oggetto> OggettoDTO buildOggettoDTO(T oggetto) {
		return OggettoDTO.builder()
			.id(oggetto.getId())
	        .categoria(oggetto.getCategoria())
	        .prezzo(oggetto.getPrezzo())
	        .descrizione(oggetto.getDescrizione())
	        .titolo(oggetto.getTitolo())
	        .dataCreazione(oggetto.getDataCreazione())
	        .dimensione(oggetto.getDimensione())
	        .autore(oggetto.getAutore())
	        .immagine(oggetto.getImmagine())
	        .isAI(oggetto.getIsAI())
			.build();
	}
}
