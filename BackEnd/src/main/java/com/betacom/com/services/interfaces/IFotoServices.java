package com.betacom.com.services.interfaces;

import java.util.List;

import com.betacom.com.dto.FotoDTO;
import com.betacom.com.exception.AcademyException;
import com.betacom.com.request.FotoReq;

public interface IFotoServices {
	List<FotoDTO> listAll();
	void createFoto(FotoReq req) throws AcademyException;
	void updateFoto(FotoReq req) throws AcademyException;
	void deleteFoto(FotoReq req) throws AcademyException;
}
