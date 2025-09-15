package com.betacom.com.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.com.dto.IllustrazioneDTO;
import com.betacom.com.exception.AcademyException;
import com.betacom.com.models.Illustrazione;
import com.betacom.com.repositories.IIllustrazioneRepository;
import com.betacom.com.request.IllustrazioneReq;
import com.betacom.com.services.interfaces.IIllustrazioneServices;
import com.betacom.com.utils.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class IllustrazioneImpl implements IIllustrazioneServices{

	private IIllustrazioneRepository illustrazioneRepository;
	
	public IllustrazioneImpl(IIllustrazioneRepository illustrazioneRepository) {
		this.illustrazioneRepository=illustrazioneRepository;
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer insert(IllustrazioneReq req) throws AcademyException {
		log.debug("create illustrazione");
		Optional<Illustrazione> i = illustrazioneRepository.findById(req.getId());
		if(i.isPresent()) throw new AcademyException();
		Illustrazione illustrazione = new Illustrazione();
		illustrazione.setStile(req.getStile());
		illustrazione.setUrlIllustrazione(req.getUrlIllustrazione());
		illustrazione.setDataIllustrazione(req.getDataIllustrazione());
		
		Utilities.verificaOggetto(req);
		
		illustrazione = Utilities.riempiOggetto(illustrazione, req);
		
		return illustrazioneRepository.save(illustrazione).getId();
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void delete(IllustrazioneReq req) throws AcademyException {
		log.debug("delete illustrazione");
		Optional<Illustrazione> i = illustrazioneRepository.findById(req.getId());
		if(i.isEmpty()) throw new AcademyException("Illustrazione non trovata: id "+ req.getId());
		
		Illustrazione illustrazioneDaEliminare = i.get();
		
		illustrazioneRepository.delete(illustrazioneDaEliminare);
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void update(IllustrazioneReq req) throws AcademyException {
		log.debug("update illustrazione");
		Optional<Illustrazione> i = illustrazioneRepository.findById(req.getId());
		if(i.isEmpty()) throw new AcademyException("Illustrazione non trovata: id "+ req.getId());
		
		Illustrazione illustrazioneDaAggiornare = i.get();
		
		if(req.getDataIllustrazione()!=null)
			illustrazioneDaAggiornare.setDataIllustrazione(req.getDataIllustrazione());
		
		if(req.getStile()!=null)
			illustrazioneDaAggiornare.setStile(req.getStile());
		
		if(req.getUrlIllustrazione()!=null)
			illustrazioneDaAggiornare.setUrlIllustrazione(req.getUrlIllustrazione());
		
		Utilities.modificaOggetto(illustrazioneDaAggiornare, req);
		
		illustrazioneRepository.save(illustrazioneDaAggiornare);
		
	}

	@Override
	public List<IllustrazioneDTO> listAll() {
		log.debug("Illustrazione listAll");
		List<Illustrazione> list= illustrazioneRepository.findAll();
		return list.stream().map(
				illustrazione -> IllustrazioneDTO.builder()
					.id(illustrazione.getId())
					.oggetto(Utilities.buildOggettoDTO(illustrazione))
					.dataIllustrazione(illustrazione.getDataIllustrazione())
					.stile(illustrazione.getStile())
					.urlIllustrazione(illustrazione.getUrlIllustrazione())
					.build()
				).collect(Collectors.toList());
	}

}
