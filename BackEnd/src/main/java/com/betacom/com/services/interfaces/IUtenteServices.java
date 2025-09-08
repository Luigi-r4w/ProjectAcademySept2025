package com.betacom.com.services.interfaces;

import java.util.List;

import com.betacom.com.dto.UtenteDTO;
import com.betacom.com.exception.AcademyException;
import com.betacom.com.request.UtenteReq;

public interface IUtenteServices {

    List<UtenteDTO> listAll();

    Integer insert(UtenteReq req) throws AcademyException;
	
	void delete(UtenteReq req) throws AcademyException;
	
	void update(UtenteReq req) throws AcademyException;

    UtenteDTO autenticazione(String email, String password) throws AcademyException;
}
