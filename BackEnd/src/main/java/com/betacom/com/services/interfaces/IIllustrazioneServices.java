package com.betacom.com.services.interfaces;

import java.util.List;

import com.betacom.com.dto.IllustrazioneDTO;
import com.betacom.com.exception.AcademyException;
import com.betacom.com.request.IllustrazioneReq;

public interface IIllustrazioneServices {
	List<IllustrazioneDTO> listAll();
	Integer insert(IllustrazioneReq req) throws AcademyException;
	void delete(IllustrazioneReq req) throws AcademyException;
	void update(IllustrazioneReq req) throws AcademyException;
}
