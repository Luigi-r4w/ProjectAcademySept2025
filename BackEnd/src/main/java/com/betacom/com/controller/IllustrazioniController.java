package com.betacom.com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.betacom.com.dto.IllustrazioneDTO;
import com.betacom.com.request.IllustrazioneReq;
import com.betacom.com.response.ResponseBase;
import com.betacom.com.response.ResponseList;
import com.betacom.com.services.interfaces.IIllustrazioneServices;

public class IllustrazioniController {
	
	private IIllustrazioneServices illustrazioneServices;

	public IllustrazioniController(IIllustrazioneServices illustrazioneServices) {
		this.illustrazioneServices = illustrazioneServices;
	}
	
	@GetMapping("/list")
	public ResponseList<IllustrazioneDTO> list() {
		ResponseList<IllustrazioneDTO> r = new ResponseList<IllustrazioneDTO>();
		try {
			r.setRc(true);
			r.setMsg(null);
			r.setList(illustrazioneServices.listAll());
		} catch (Exception e) {
			r.setRc(true);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/insert")
	public ResponseBase create(@RequestBody (required = true) IllustrazioneReq req) {
		ResponseBase r = new ResponseBase();
		try {
			illustrazioneServices.insert(req);
			r.setMsg(null);
			r.setRc(true);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PutMapping("/update")
	public ResponseBase update(@RequestBody (required = true) IllustrazioneReq req) {
		ResponseBase r = new ResponseBase();
		try {
			illustrazioneServices.update(req);
			r.setMsg(null);
			r.setRc(true);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody (required = true) IllustrazioneReq req) {
		ResponseBase r = new ResponseBase();
		try {
			illustrazioneServices.delete(req);
			r.setMsg(null);
			r.setRc(true);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
}
