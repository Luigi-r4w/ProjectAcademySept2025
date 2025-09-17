package com.betacom.com.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.com.dto.OggettoDTO;
import com.betacom.com.dto.UtenteDTO;
import com.betacom.com.exception.AcademyException;
import com.betacom.com.models.Oggetto;
import com.betacom.com.models.Utente;
import com.betacom.com.repositories.IOggettoRepository;
import com.betacom.com.repositories.IUtenteRepository;
import com.betacom.com.request.UtenteReq;
import com.betacom.com.services.interfaces.IUtenteServices;
import com.betacom.com.utils.Roles;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UtenteImpl implements IUtenteServices{
    
    private IUtenteRepository utenteRepository;
    private IOggettoRepository oggettoRepository;
	
	public UtenteImpl(IUtenteRepository utenteRepository, IOggettoRepository oggettoRepository) {
		this.utenteRepository=utenteRepository;
        this.oggettoRepository=oggettoRepository;
	}

    @Override
    public List<UtenteDTO> listAll() {
       log.debug("Utente listAll");
       List<Utente> lU = utenteRepository.findAll();
		return lU.stream().map(u -> UtenteDTO.builder()
                .id(u.getId())
                .nome(u.getNome())
                .email(u.getEmail())
                .password(u.getPassword())
                .carrello(buildCarrello(u.getCarrello()))
                .role(u.getRole().toString())
                .build()
                ).collect(Collectors.toList());
    }

    @Override
    public Integer insert(UtenteReq req) throws AcademyException {
        log.debug("Utente Insert : "+req);
        Optional<Utente> u = utenteRepository.findByEmail(req.getEmail());
		if (u.isPresent()) {
			throw new AcademyException("email già utilizzata");
		}
        Utente utente = new Utente();
        if(req.getEmail().isEmpty())
        	throw new AcademyException("email non inserita");
        if(req.getPassword().isEmpty())
        	throw new AcademyException("password non inserita");
        utente.setNome(req.getNome());
        utente.setEmail(req.getEmail());
        utente.setPassword(req.getPassword());
        utente.setRole(Roles.valueOf(req.getRole()));
        return utenteRepository.save(utente).getId();
    }

    @Override
    public void delete(UtenteReq req) throws AcademyException {
        log.debug("Utente delete : "+req);
        Optional<Utente> u = utenteRepository.findById(req.getId());
		if (u.isEmpty()) {
			throw new AcademyException("socio non trovato");
		}
		utenteRepository.delete(u.get());    }

    @Override
    public void update(UtenteReq req) throws AcademyException {
        log.debug("Utente update : "+req);
        Optional<Utente> s = utenteRepository.findById(req.getId());
		if (s.isEmpty()) {
			throw new AcademyException("utente non trovato");
		}
		Utente u = s.get();
		if(req.getEmail()!=null && !req.getEmail().trim().isEmpty()){
			Optional<Utente> e = utenteRepository.findByEmail(req.getEmail());
		    if (e.isPresent()) {
			    throw new AcademyException("email già utilizzata");
		    }
            u.setEmail(req.getEmail());
        }
		if(req.getPassword()!=null && !req.getPassword().trim().isEmpty())
			u.setPassword(req.getPassword());
		if(req.getNome()!=null && !req.getNome().trim().isEmpty())
			u.setNome(req.getNome());
		utenteRepository.save(u);	    
    }

    @Override
    public UtenteDTO autenticazione(String email, String password) throws AcademyException {
        log.debug("Utente autenticazione : "+email+" : "+password);
        Optional<Utente> e = utenteRepository.findByEmail(email);
        if (e.isEmpty()) {
            throw new AcademyException("email non trovata");
        }
        Utente u = e.get();
        if (!u.getPassword().equals(password)) {
            throw new AcademyException("password non corretta");
        }
        return UtenteDTO.builder()
            .id(u.getId())
            .nome(u.getNome())
            .email(u.getEmail())
            .password(u.getPassword())
            .carrello(buildCarrello(u.getCarrello()))
            .role(u.getRole().toString())
            .build();
    }

    public List<OggettoDTO> buildCarrello(List<Oggetto> lo){
		return lo.stream()
		.map(o -> OggettoDTO.builder()
				.id(o.getId())
                .categoria(o.getCategoria())
                .prezzo(o.getPrezzo())
                .descrizione(o.getDescrizione())
                .titolo(o.getTitolo())
                .dataCreazione(o.getDataCreazione())
                .dimensione(o.getDimensione())
                .autore(o.getAutore())
                .immagine(o.getImmagine())
                .isAI(o.getIsAI())
				.build()
				)
		.collect(Collectors.toList());
	}

    @Override
    public void addAlCarrello(Integer utenteId, Integer oggettoId) throws AcademyException {
        Optional<Utente> u = utenteRepository.findById(utenteId);
        if(u.isEmpty()){
            throw new AcademyException("utente non trovato ");
        }
        Optional<Oggetto> o = oggettoRepository.findById(oggettoId);
        if(u.isEmpty()){
            throw new AcademyException("oggetto non trovato ");
        }
        u.get().getCarrello().add(o.get());
        utenteRepository.save(u.get());
    }

    @Override
    public void rmDalCarrello(Integer utenteId, Integer oggettoId) throws AcademyException {
        Optional<Utente> u = utenteRepository.findById(utenteId);
        if(u.isEmpty()){
            throw new AcademyException("utente non trovato ");
        }
        Optional<Oggetto> o = oggettoRepository.findById(oggettoId);
        if(u.isEmpty()){
            throw new AcademyException("oggetto non trovato ");
        }
        u.get().getCarrello().remove(o.get());
        utenteRepository.save(u.get());
    }

    @Override
    public UtenteDTO findById(Integer id) throws AcademyException {
        Optional<Utente> optionalU = utenteRepository.findById(id);
        if(optionalU.isEmpty()){
            throw new AcademyException("utente non trovato ");
        }
        Utente u = optionalU.get();
        return UtenteDTO.builder()
            .id(u.getId())
            .nome(u.getNome())
            .email(u.getEmail())
            .password(u.getPassword())
            .carrello(buildCarrello(u.getCarrello()))
            .role(u.getRole().toString())
            .build();
    }

	@Override
	public void svuotaCarrello(Integer utenteId) throws AcademyException {
		Optional<Utente> optionalU = utenteRepository.findById(utenteId);
        if(optionalU.isEmpty()){
            throw new AcademyException("utente non trovato ");
        }
        Utente u = optionalU.get();
        u.getCarrello().clear();
        utenteRepository.save(u);
	}
    
}
