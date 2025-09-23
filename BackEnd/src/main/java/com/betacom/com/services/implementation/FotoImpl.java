package com.betacom.com.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.com.dto.FotoDTO;
import com.betacom.com.exception.AcademyException;
import com.betacom.com.models.Foto;
import com.betacom.com.repositories.IFotoRepository;
import com.betacom.com.request.FotoReq;
import com.betacom.com.services.interfaces.IFotoServices;
import com.betacom.com.utils.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class FotoImpl implements IFotoServices{
	
	private IFotoRepository fotoR;
	
	public FotoImpl(IFotoRepository fotoR) {
		super();
		this.fotoR = fotoR;
	}
	
	@Override
	public List<FotoDTO> listAll() {
		log.debug("listAll() foto");
		
		List<Foto> fotoList = fotoR.findAll();
		
		return fotoList.stream()
				.map(f -> FotoDTO.builder()
						.id(f.getId())
						.device(f.getDevice())
						.widthResolution(f.getWidthResolution())
						.heightResolution(f.getHeightResolution())
						.oggetto(Utilities.buildOggettoDTO(f))
				.build()).collect(Collectors.toList());
	}
	
	@Transactional (rollbackFor = Exception.class)
	@Override
	public void createFoto(FotoReq req) throws AcademyException {
		log.debug("createFoto: " + req);
		
		// verifico i parametri che siano diversi da null
		Utilities.verificaOggetto(req);
		
		// riempo i parametri oggetto
		Foto foto = new Foto();
		
		foto = Utilities.riempiOggetto(foto, req);
		
		if(req.getDevice() == null)
			throw new AcademyException("Device obbligatorio");
		
		if(req.getWidthResolution() == null)
			throw new AcademyException("Width-Resolution obbligatorio");
		
		if(req.getHeightResolution() == null)
			throw new AcademyException("Height-Resolution obbligatorio");
		
		foto.setDevice(req.getDevice());
		foto.setWidthResolution(req.getWidthResolution());
		foto.setHeightResolution(req.getHeightResolution());
		
		fotoR.save(foto);
	}
	
	@Transactional (rollbackFor = Exception.class)
	@Override
	public void deleteFoto(FotoReq req) throws AcademyException {
		log.debug("deleteFoto: " + req);
		
		Optional<Foto> foto = fotoR.findById(req.getId());
		
		if(foto.isEmpty())
			throw new AcademyException("Foto non esistente");
		
		fotoR.delete(foto.get());
		
	}
	
	@Transactional (rollbackFor = Exception.class)
	@Override
	public void updateFoto(FotoReq req) throws AcademyException {
		log.debug("updateFoto: " + req);
		
		Optional<Foto> f = fotoR.findById(req.getId());
		
		if(f.isEmpty())
			throw new AcademyException("Foto non esistente");
		
		Foto foto = f.get();
		
		foto = Utilities.modificaOggetto(foto, req);
		
		if(req.getDevice() != null)
			foto.setDevice(req.getDevice());
		
		if(req.getWidthResolution() != null)
			foto.setWidthResolution(req.getWidthResolution());
		
		if(req.getHeightResolution() != null)
			foto.setHeightResolution(req.getHeightResolution());
		
		fotoR.save(foto);
		
	}

	@Override
	public FotoDTO getByID(Integer id) throws AcademyException {
		log.debug("getByID: " + id);
		
		Optional<Foto> foto = fotoR.findById(id);
		
		if(foto.isEmpty())
			throw new AcademyException("Foto non esistente");
		
		Foto f = foto.get();
		
		return FotoDTO.builder()
				.id(f.getId())
				.device(f.getDevice())
				.widthResolution(f.getWidthResolution())
				.heightResolution(f.getHeightResolution())
				.oggetto(Utilities.buildOggettoDTO(f))
		.build();
	}	
	
}
