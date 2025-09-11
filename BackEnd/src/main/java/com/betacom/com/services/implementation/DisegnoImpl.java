package com.betacom.com.services.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.com.dto.DisegnoDTO;
import com.betacom.com.exception.AcademyException;
import com.betacom.com.models.Disegno;
import com.betacom.com.repositories.IDisegnoRepository;
import com.betacom.com.request.DisegnoReq;
import com.betacom.com.services.interfaces.IDisegnoServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class DisegnoImpl implements IDisegnoServices{
	private IDisegnoRepository disR;
	
	public DisegnoImpl(IDisegnoRepository disR) {
		super();
		this.disR = disR;
	}

	@Override
	public List<DisegnoDTO> listAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional (rollbackFor = Exception.class)
	@Override
	public Integer insert(DisegnoReq req) throws AcademyException {
		log.debug("Insert :" + req);
		
		Disegno dis = new Disegno();
		
		if (req.getAutore() == null)
			throw new AcademyException("Autore obbligatorio");
		if (req.getCategoria() == null)
			throw new AcademyException("Categoria obbligatoria");
		if (req.getDescrizione() == null)
			throw new AcademyException("Descrizione obbligatoria");
		if (req.getDimensione() == null)
			throw new AcademyException("Dimensione obbligatoria");
		if (req.getImmagine() == null)
			throw new AcademyException("Immagine obbligatoria");
		if (req.getIsAI() == null)
			throw new AcademyException("IsAi obbligatorio");
		if (req.getPrezzo() == null)
			throw new AcademyException("Prezzo obbligatorio");
		if (req.getTitolo() == null)
			throw new AcademyException("Titolo obbligatorio");
		if (req.getSupporto() == null)
			throw new AcademyException("Supporto obbligatorio");
		if (req.getTecnica() == null)
			throw new AcademyException("Tecnica obbligatoria");
		
		dis.setAutore(req.getAutore());
		//dis.setCategoria(req.getCategoria());
		if (req.getDataCreazione() == null)
			dis.setDataCreazione(LocalDate.now());
		else
			dis.setDataCreazione(req.getDataCreazione());
		dis.setDescrizione(req.getDescrizione());
		dis.setDimensione(req.getDimensione());
		dis.setImmagine(req.getImmagine());
		dis.setIsAI(req.getIsAI());
		dis.setPrezzo(req.getPrezzo());
		dis.setTitolo(req.getTitolo());
		dis.setSupporto(req.getSupporto());
		dis.setTecnica(req.getTecnica());
		
		Integer idDisegno = disR.save(dis).getId();
		
		return idDisegno;
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
			throw new AcademyException("Disegno non trovato nel database :" + req.getId());
		Disegno dis = d.get();
		
		if (req.getAutore() != null)
			dis.setAutore(req.getAutore());
		if (req.getCategoria() != null)
			//dis.setCategoria(req.getCategoria());
		if (req.getDataCreazione() != null)
			dis.setDataCreazione(req.getDataCreazione());
		if (req.getDescrizione() != null)
			dis.setDescrizione(req.getDescrizione());
		if (req.getDimensione() != null)
			dis.setDimensione(req.getDimensione());
		if (req.getImmagine() != null)
			dis.setImmagine(req.getImmagine());
		if (req.getIsAI() != null)
			dis.setIsAI(req.getIsAI());
		if (req.getPrezzo() != null)
			dis.setPrezzo(req.getPrezzo());
		if (req.getTitolo() != null)
			dis.setTitolo(req.getTitolo());
		if (req.getSupporto() != null)
			dis.setSupporto(req.getSupporto());
		if (req.getTecnica() != null)
			dis.setTecnica(req.getTecnica());

		disR.save(dis);
	}

}
