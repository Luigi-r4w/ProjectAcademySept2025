package com.betacom.com.services.interfaces;

import com.betacom.com.exception.AcademyException;
import com.betacom.com.request.FotoReq;

public interface IFotoServices {
	void createFoto(FotoReq req) throws AcademyException;
	void updateFoto(FotoReq req) throws AcademyException;
	void deleteFoto(FotoReq req) throws AcademyException;
}
