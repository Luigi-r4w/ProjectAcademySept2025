package com.betacom.com.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.com.request.DisegnoReq;
import com.betacom.com.response.ResponseBase;
import com.betacom.com.services.interfaces.IDisegnoServices;

@RestController
@RequestMapping("/rest/disegno")
public class DisegnoController {
	IDisegnoServices disS;
	
	public DisegnoController(IDisegnoServices disS) {
		super();
		this.disS = disS;
	}


	@PostMapping("create")
	public ResponseBase create(@RequestBody (required = true)  DisegnoReq req) {
		ResponseBase r = new ResponseBase();
		try {
			disS.insert(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PutMapping("update")
	public ResponseBase update(@RequestBody (required = true)  DisegnoReq req) {
		ResponseBase r = new ResponseBase();
		try {
			disS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("delete")
	public ResponseBase delete(@RequestBody (required = true)  DisegnoReq req) {
		ResponseBase r = new ResponseBase();
		try {
			disS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
}
