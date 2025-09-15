package com.betacom.com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.com.dto.FotoDTO;
import com.betacom.com.request.FotoReq;
import com.betacom.com.response.ResponseBase;
import com.betacom.com.response.ResponseList;
import com.betacom.com.services.interfaces.IFotoServices;

@RestController
@RequestMapping("/rest/foto")
public class FotoController {
	
	private IFotoServices fotoS;

	public FotoController(IFotoServices fotoS) {
		super();
		this.fotoS = fotoS;
	}
	
	@GetMapping("/list")
	public ResponseList<FotoDTO> list() {
		ResponseList<FotoDTO> r = new ResponseList<FotoDTO>();
		try {
			r.setRc(true);
			r.setMsg(null);
			r.setList(fotoS.listAll());
		} catch (Exception e) {
			r.setRc(true);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/insert")
	public ResponseBase create(@RequestBody (required = true) FotoReq req) {
		ResponseBase r = new ResponseBase();
		try {
			fotoS.createFoto(req);
			r.setMsg(null);
			r.setRc(true);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PutMapping("/update")
	public ResponseBase update(@RequestBody (required = true) FotoReq req) {
		ResponseBase r = new ResponseBase();
		try {
			fotoS.updateFoto(req);
			r.setMsg(null);
			r.setRc(true);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody (required = true) FotoReq req) {
		ResponseBase r = new ResponseBase();
		try {
			fotoS.deleteFoto(req);
			r.setMsg(null);
			r.setRc(true);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
	
}
