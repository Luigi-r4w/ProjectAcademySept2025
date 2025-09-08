package com.betacom.com.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.com.dto.UtenteDTO;
import com.betacom.com.exception.AcademyException;
import com.betacom.com.models.Utente;
import com.betacom.com.repositories.IUtenteRepository;
import com.betacom.com.request.UtenteReq;
import com.betacom.com.services.interfaces.IUtenteServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UtenteImpl implements IUtenteServices{
    
    private IUtenteRepository utenteRepository;
	
	public UtenteImpl(IUtenteRepository utenteRepository) {
		this.utenteRepository=utenteRepository;
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
                .carrello(u.getCarrello())
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
        utente.setCarrello(req.getCarrello());
        utente.setNome(req.getNome());
        utente.setEmail(req.getEmail());
        utente.setPassword(req.getPassword());
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
			throw new AcademyException("socio non trovato");
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
        if(req.getCarrello()!=null && req.getCarrello().length!=0)
            u.setCarrello(req.getCarrello());
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
            .carrello(u.getCarrello())
            .build();
    }
    
}
