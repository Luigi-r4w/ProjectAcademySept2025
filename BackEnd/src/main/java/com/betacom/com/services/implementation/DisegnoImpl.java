package com.betacom.com.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.com.dto.DisegnoDTO;
import com.betacom.com.exception.AcademyException;
import com.betacom.com.models.Disegno;
import com.betacom.com.repositories.IDisegnoRepository;
import com.betacom.com.request.DisegnoReq;
import com.betacom.com.services.interfaces.IDisegnoServices;
import com.betacom.com.utils.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class DisegnoImpl extends Utilities implements IDisegnoServices{
	private IDisegnoRepository disR;
	
	public DisegnoImpl(IDisegnoRepository disR) {
		super();
		this.disR = disR;
	}

	@Override
	public List<DisegnoDTO> listAll() {
		
		List<Disegno> lD = disR.findAll();
		
		return lD.stream()
				.map(d -> DisegnoDTO.builder()
						.id(d.getId())
						.tecnica(d.getTecnica())
						.supporto(d.getSupporto())
						.oggetto(buildOggettoDTO(d))
						.build())
				.collect(Collectors.toList());
	}
	
	@Transactional (rollbackFor = Exception.class)
	@Override
	public Integer insert(DisegnoReq req) throws AcademyException {
		log.debug("Insert :" + req);
		
		Disegno dis = new Disegno();
		
		verificaOggetto(req);
		
		if (req.getSupporto() == null)
			throw new AcademyException("Supporto obbligatorio");
		if (req.getTecnica() == null)
			throw new AcademyException("Tecnica obbligatoria");
		
		dis = riempiOggetto(dis, req);
		
		dis.setSupporto(req.getSupporto());
		dis.setTecnica(req.getTecnica());
		
		return disR.save(dis).getId();
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public void delete(DisegnoReq req) throws AcademyException {
		log.debug("delete :" + req);
		Optional<Disegno> d = disR.findById(req.getId());
		
		if (d.isEmpty())
			throw new AcademyException("Disegno non trovato nel database");
		
		disR.delete(d.get());
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public void update(DisegnoReq req) throws AcademyException {
		log.debug("Update :" + req);
		Optional<Disegno> d = disR.findById(req.getId());
		
		if (d.isEmpty())
			throw new AcademyException("Disegno non trovato nel database");
		Disegno dis = d.get();
		
		dis = modificaOggetto(dis, req);
		
		if (req.getSupporto() != null)
			dis.setSupporto(req.getSupporto());
		if (req.getTecnica() != null)
			dis.setTecnica(req.getTecnica());

		disR.save(dis);
	}
	
	@Override
	public DisegnoDTO findById(Integer id) throws AcademyException {
		log.debug("findById :" + id);
		Optional<Disegno> dis = disR.findById(id);
		
		if (dis.isEmpty())
			throw new AcademyException("Disegno non trovato nel database");
		Disegno d = dis.get();
	
		return DisegnoDTO.builder()
				.id(d.getId())
				.tecnica(d.getTecnica())
				.supporto(d.getSupporto())
				.oggetto(buildOggettoDTO(d))
				.build();
	}
}
