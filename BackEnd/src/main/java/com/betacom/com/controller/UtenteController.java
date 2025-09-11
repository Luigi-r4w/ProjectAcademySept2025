package com.betacom.com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.com.dto.UtenteDTO;
import com.betacom.com.request.UtenteReq;
import com.betacom.com.response.ResponseBase;
import com.betacom.com.response.ResponseList;
import com.betacom.com.response.ResponseObject;
import com.betacom.com.services.interfaces.IUtenteServices;


@RestController
@RequestMapping("/rest/utente")
public class UtenteController {

    private IUtenteServices utenteService;
	
	public UtenteController(IUtenteServices utenteS) {
		this.utenteService=utenteS;
	}

    @GetMapping("/list")
	public ResponseList<UtenteDTO> list() {
		ResponseList<UtenteDTO> r = new ResponseList<UtenteDTO>();
		try {
			r.setRc(true);
			r.setMsg(null);
			r.setList(utenteService.listAll());
		} catch (Exception e) {
			r.setRc(true);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	@GetMapping("/fingById")
	public ResponseObject<UtenteDTO> findById(@RequestParam Integer id){
		ResponseObject<UtenteDTO> r = new ResponseObject<UtenteDTO>();
		try {
			r.setRc(true);
			r.setMsg(null);
			r.setDati(utenteService.findById(id));
		} catch (Exception e) {
			r.setRc(true);
			r.setMsg(e.getMessage());
		}
		return r;
	}

    @PostMapping("/insert")
	public ResponseBase create(@RequestBody (required = true) UtenteReq sR) {
		ResponseBase r = new ResponseBase();
		try {
			utenteService.insert(sR);
			r.setMsg(null);
			r.setRc(true);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PutMapping("/update")
	public ResponseBase update(@RequestBody (required = true) UtenteReq sR) {
		ResponseBase r = new ResponseBase();
		try {
			utenteService.update(sR);
			r.setMsg(null);
			r.setRc(true);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody (required = true) UtenteReq sR) {
		ResponseBase r = new ResponseBase();
		try {
			utenteService.delete(sR);
			r.setMsg(null);
			r.setRc(true);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
    
	@PostMapping("/login")
    public ResponseObject<UtenteDTO> login(@RequestParam String email, @RequestParam String password) {
        ResponseObject<UtenteDTO> r = new ResponseObject<>();
        try{
            UtenteDTO utente = utenteService.autenticazione(email, password);
            r.setDati(utente);
            r.setMsg(null);
            r.setRc(true);
        }catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
        return r;
    }

	@PostMapping("/aggAlCarrello")
	public ResponseBase aggiungiAlCarrello(@RequestParam Integer utenteId,@RequestParam Integer oggettoId){
		ResponseBase r = new ResponseBase();
		try{
            utenteService.addAlCarrello(utenteId, oggettoId);
            r.setMsg(null);
            r.setRc(true);
        }catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

	@PostMapping("/rmDalCarrello")
	public ResponseBase rimuoviDalCarrello(@RequestParam Integer utenteId,@RequestParam Integer oggettoId){
		ResponseBase r = new ResponseBase();
		try{
            utenteService.rmDalCarrello(utenteId, oggettoId);
            r.setMsg(null);
            r.setRc(true);
        }catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

}
