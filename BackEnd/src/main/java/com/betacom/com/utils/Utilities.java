package com.betacom.com.utils;

import java.time.LocalDate;

import com.betacom.com.exception.AcademyException;
import com.betacom.com.models.Oggetto;
import com.betacom.com.request.OggettoReq;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Utilities {
	
	public static <R extends OggettoReq> void verificaOggetto(R req) throws AcademyException{
		
		if (req.getPrezzo() == null || req.getPrezzo().isBlank())
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
		log.debug("modificaOggetto: " + req);
		if (req.getPrezzo() != null)
			oggetto.setPrezzo(req.getPrezzo());
		if (req.getDescrizione()!= null)
			oggetto.setDescrizione(req.getDescrizione());
		if (req.getTitolo()!= null)
			oggetto.setTitolo(req.getTitolo());
		if (req.getDataCreazione() != null)
			oggetto.setDataCreazione(req.getDataCreazione());
		if (req.getDimensione() != null)
			oggetto.setDimensione(req.getDimensione());
		if (req.getAutore() != null)
			oggetto.setAutore(req.getAutore());
		if (req.getImmagine() != null)
			oggetto.setImmagine(req.getImmagine());
		if (req.getIsAI() != null)
			oggetto.setIsAI(req.getIsAI());
		
		return oggetto;
	}
}
