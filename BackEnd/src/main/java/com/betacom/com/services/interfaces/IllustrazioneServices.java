package com.betacom.com.services.interfaces;

import com.betacom.com.exception.AcademyException;
import com.betacom.com.request.IllustrazioneReq;

public interface IllustrazioneServices {
	Integer insert(IllustrazioneReq req) throws AcademyException;
	void delete(IllustrazioneReq req) throws AcademyException;
	void update(IllustrazioneReq req) throws AcademyException;
}
