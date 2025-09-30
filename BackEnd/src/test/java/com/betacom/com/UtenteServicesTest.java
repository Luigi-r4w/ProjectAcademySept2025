package com.betacom.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.betacom.com.controller.IllustrazioneController;
import com.betacom.com.controller.UtenteController;
import com.betacom.com.dto.UtenteDTO;
import com.betacom.com.request.IllustrazioneReq;
import com.betacom.com.request.UtenteReq;
import com.betacom.com.response.ResponseBase;
import com.betacom.com.response.ResponseObject;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@DirtiesContext
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtenteServicesTest {

    @Autowired
	private UtenteController utenteController;

    @Test
	@Order(1)
    public void nuovoUtente() {
        UtenteReq utente = new UtenteReq();
        utente.setEmail("email");
        utente.setPassword("pwd");
        utente.setNome("nome");
        utente.setRole("ADMIN");
        ResponseBase rb = utenteController.create(utente);
        assertTrue(rb.getRc(), "Creazione avvenuta con successo");
    }

    @Test
	@Order(2)
    public void updateUtente() {
        UtenteReq utente = new UtenteReq();
        utente.setId(1);
        utente.setEmail("email5");
        ResponseBase rb = utenteController.aggiungiAlCarrello(5, 1);
        rb = utenteController.update(utente);
        assertTrue(rb.getRc(), "Update avvenuta con successo");
    }

    @Test
	@Order(3)
    public void findByIdUtente() {
        ResponseObject<UtenteDTO> rb = utenteController.findById(1);
        System.out.println(rb.getDati());
        System.out.println(rb.getMsg());
        System.out.println(rb.getRc());
        assertEquals("nome", rb.getDati().getNome(), "Il nome dell'utente dovrebbe essere 'nome'.");
    }

    @Test
	@Order(4)
    public void deleteUtente() {
        UtenteReq utente = new UtenteReq();
        utente.setId(1);
        ResponseBase rb = utenteController.delete(utente);
        assertTrue(rb.getRc(), "Delete avvenuta con successo");
    }

    @Test
	@Order(5)
    public void nuovoUtenteEr() {
        UtenteReq utente = new UtenteReq();
        utente.setNome(null);
        ResponseBase rb = utenteController.create(utente);
        assertFalse( rb.getRc() );
    }

    @Test
	@Order(6)
    public void updateUtenteEr() {
        UtenteReq utente = new UtenteReq();
        utente.setId(99);
        utente.setEmail("email2");
        ResponseBase rb = utenteController.update(utente);
        assertFalse( rb.getRc());
    }

    @Test
	@Order(7)
    public void findByIdUtenteEr() {
        ResponseObject<UtenteDTO> rb = utenteController.findById(99);
        assertFalse( rb.getRc());
    }

    @Test
	@Order(8)
    public void deleteUtenteEr() {
        UtenteReq utente = new UtenteReq();
        utente.setId(99);
        ResponseBase rb = utenteController.delete(utente);
        assertFalse(rb.getRc(), "Delete non avvenuto con successo");
    }
    
    @Test
	@Order(9)
    public void nuovoUtenteError() {
    	 UtenteReq utente = new UtenteReq();
         utente.setEmail("admin");
         utente.setPassword("admin");
         utente.setNome("admin");
         utente.setRole("ADMIN");
         ResponseBase rb = utenteController.create(utente);
         rb = utenteController.create(utente);
         Assertions.assertThat(rb.getRc()).isEqualTo(false);
    }
    
    @Test
    @Order(10)
    public void listAll() {
    	ResponseBase rb = utenteController.list();
    	Assertions.assertThat(rb.getRc()).isEqualTo(true);
    }
    @Test
    @Order(11)
    public void autenticate() {
    	UtenteReq ur = new UtenteReq();
    	ur.setEmail("admin"); ur.setPassword("admin");
    	ResponseBase rb = utenteController.login(ur);
    	Assertions.assertThat(rb.getRc()).isEqualTo(true);
    }
    
    @Autowired
	private IllustrazioneController illustrazioneC;
    
    @Test
    @Order(12)
    public void aggiungiAlCarrello() {
    	IllustrazioneReq req = new IllustrazioneReq();
		req.setCategoria("illustrazione");
		req.setPrezzo(20d); 
		req.setDescrizione("Arte troppo moderna"); 
		req.setTitolo("Pane al latte"); 
		req.setDataCreazione(LocalDate.of(2025, 9, 15)); 
		req.setDimensione("1920x1080"); 
		req.setAutore("Alex"); 
		req.setImmagine("bread.jpg"); 
		req.setIsAI(false);
		req.setDataIllustrazione(LocalDate.MIN);
		req.setStile("bread");
		req.setUrlIllustrazione("pijama.html");
		illustrazioneC.create(req);
		
		ResponseBase rb = utenteController.aggiungiAlCarrello(2, 1);
		Assertions.assertThat(rb.getRc()).isEqualTo(true);
		
		rb = utenteController.aggiungiAlCarrello(2, 2);
		Assertions.assertThat(rb.getRc()).isEqualTo(false);	
    }
    
    @Test
    @Order(12)
    public void rimuoviDalCarrello() {
    	utenteController.aggiungiAlCarrello(2, 1);
    	ResponseBase rb = utenteController.rimuoviDalCarrello(2, 1);
		Assertions.assertThat(rb.getRc()).isEqualTo(true);
    }
    @Test
    @Order(13)
    public void svuotaCarrello() {
    	ResponseBase rb = utenteController.aggiungiAlCarrello(2, 1);
    	rb = utenteController.svuotaCarello(2);
		Assertions.assertThat(rb.getRc()).isEqualTo(true);
    }

}
