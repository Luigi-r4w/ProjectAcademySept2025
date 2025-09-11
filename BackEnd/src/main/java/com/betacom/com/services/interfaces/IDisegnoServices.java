package com.betacom.com.services.interfaces;

import java.util.List;

import com.betacom.com.dto.DisegnoDTO;
import com.betacom.com.exception.AcademyException;
import com.betacom.com.request.DisegnoReq;

public interface IDisegnoServices {
	List<DisegnoDTO> listAll();

    Integer insert(DisegnoReq req) throws AcademyException;
	
	void delete(DisegnoReq req) throws AcademyException;
	
	void update(DisegnoReq req) throws AcademyException;
}
