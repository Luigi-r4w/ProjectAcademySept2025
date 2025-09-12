package com.betacom.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.betacom.com.controller.UtenteController;
import com.betacom.com.dto.UtenteDTO;
import com.betacom.com.request.UtenteReq;
import com.betacom.com.response.ResponseBase;
import com.betacom.com.response.ResponseObject;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtenteServicesTest {

//    @Autowired
//	private UtenteController utenteController;
//
//    @Test
//	@Order(1)
//    public void nuovoUtente() {
//        UtenteReq utente = new UtenteReq();
//        utente.setEmail("email");
//        utente.setPassword("pwd");
//        utente.setNome("nome");
//        ResponseBase rb = utenteController.create(utente);
//        assertTrue(rb.getRc(), "Creazione avvenuta con successo");
//    }
//
//    @Test
//	@Order(2)
//    public void updateUtente() {
//        UtenteReq utente = new UtenteReq();
//        utente.setId(1);
//        utente.setEmail("email5");
//        ResponseBase rb = utenteController.aggiungiAlCarrello(5, 1);
//        rb = utenteController.update(utente);
//        System.out.println(rb.getMsg());
//        assertTrue(rb.getRc(), "Update avvenuta con successo");
//    }
//
//    @Test
//	@Order(3)
//    public void findByIdUtente() {
//        ResponseObject<UtenteDTO> rb = utenteController.findById(1);
//        System.out.println(rb.getDati());
//        System.out.println(rb.getMsg());
//        System.out.println(rb.getRc());
//
//        assertEquals("nome", rb.getDati().getNome(), "Il nome dell'utente dovrebbe essere 'nome'.");
//    }
//
//    @Test
//	@Order(4)
//    public void deleteUtente() {
//        UtenteReq utente = new UtenteReq();
//        utente.setId(1);
//        ResponseBase rb = utenteController.delete(utente);
//        assertTrue(rb.getRc(), "Delete avvenuta con successo");
//    }
//
//    @Test
//	@Order(5)
//    public void nuovoUtenteEr() {
//        UtenteReq utente = new UtenteReq();
//        utente.setNome(null);
//        ResponseBase rb = utenteController.create(utente);
//        assertFalse( rb.getRc() );
//    }
//
//    @Test
//	@Order(6)
//    public void updateUtenteEr() {
//        UtenteReq utente = new UtenteReq();
//        utente.setId(99);
//        utente.setEmail("email2");
//        ResponseBase rb = utenteController.update(utente);
//        assertFalse( rb.getRc());
//    }
//
//    @Test
//	@Order(7)
//    public void findByIdUtenteEr() {
//        ResponseObject<UtenteDTO> rb = utenteController.findById(99);
//        assertFalse( rb.getRc());
//    }
//
//    @Test
//	@Order(8)
//    public void deleteUtenteEr() {
//        UtenteReq utente = new UtenteReq();
//        utente.setId(99);
//        ResponseBase rb = utenteController.delete(utente);
//        assertFalse(rb.getRc(), "Delete non avvenuto con successo");
//    }

}
