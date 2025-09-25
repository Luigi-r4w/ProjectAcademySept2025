package com.betacom.com;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.betacom.com.controller.IllustrazioneController;
import com.betacom.com.dto.IllustrazioneDTO;
import com.betacom.com.exception.AcademyException;
import com.betacom.com.request.IllustrazioneReq;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IllustrazioneServicesTest {
	
	@Autowired
	private IllustrazioneController illustrazioneC;
	
	@Test
	@Order(1)
	void createIllustrazione() throws AcademyException{
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
		
		IllustrazioneDTO i = illustrazioneC.list().getList().stream()
				.filter(e -> e.getId() == 1)
				.findFirst()		
				.orElseThrow(() -> new AssertionError("Illustrazione non trovata"));
		
		Assertions.assertThat(i.getId()).isEqualTo(1);
	}
	@Test
	@Order(2)
	void update() throws AcademyException{
		IllustrazioneReq req = new IllustrazioneReq();
		req.setId(1);
		req.setPrezzo(300d);
		req.setDataIllustrazione(LocalDate.MAX);
		req.setStile("milk");
		req.setUrlIllustrazione("bread&milk.png");
		
		req.setCategoria("illustrazione");
		req.setDescrizione("Arte non abbastanza moderna"); 
		req.setTitolo("Pane al latte tante nanne"); 
		req.setDataCreazione(LocalDate.of(2025, 9, 15)); 
		req.setDimensione("1920x1080"); 
		req.setAutore("Qualcun'altro"); 
		req.setImmagine("bread.jpg"); 
		req.setIsAI(true);
		
		illustrazioneC.update(req);
		
		IllustrazioneDTO i = illustrazioneC.list().getList().stream()
				.filter(e -> e.getId() == 1)
				.findFirst()		
				.orElseThrow(() -> new AssertionError("Illustrazione non trovata"));
		
		Assertions.assertThat(i.getOggetto().getPrezzo()).isEqualTo(300d);
		Assertions.assertThat(i.getOggetto().getTitolo()).isEqualTo("Pane al latte tante nanne");
	}
	@Test
	@Order(3)
	void failedUpdate() throws AcademyException{
		IllustrazioneReq req = new IllustrazioneReq();
		req.setId(3);
		req.setPrezzo(500d);
		
		Assertions.assertThat(illustrazioneC.update(req).getRc()).isEqualTo(false);
	}
	@Test
	@Order(3)
	void partialUpdate() throws AcademyException{
		IllustrazioneReq req = new IllustrazioneReq();
		req.setId(1);
		req.setPrezzo(500d);
		
		Assertions.assertThat(illustrazioneC.update(req).getRc()).isEqualTo(true);
		IllustrazioneDTO i = illustrazioneC.list().getList().stream()
				.filter(e -> e.getId() == 1)
				.findFirst()		
				.orElseThrow(() -> new AssertionError("Illustrazione non trovata"));
		Assertions.assertThat(i.getOggetto().getPrezzo()).isEqualTo(500d);
	}
	
	@Test
	@Order(5)
	void delete() throws AcademyException{
		IllustrazioneReq req = new IllustrazioneReq();
		req.setId(1);
		
		illustrazioneC.delete(req);
		
		Assertions.assertThat(illustrazioneC.list().getList().isEmpty());
	}
	@Test
	@Order(6)
	void failedDelete() throws AcademyException{
		IllustrazioneReq req = new IllustrazioneReq();
		req.setId(1);
		
		Assertions.assertThat(illustrazioneC.delete(req).getRc()).isEqualTo(false);
		
		Assertions.assertThat(illustrazioneC.list().getList().isEmpty());
	}
	
	@Test
	@Order(7)
	void failedInsertions() throws AcademyException{
		IllustrazioneReq req = new IllustrazioneReq();
		
		req.setStile("bread");
		Assertions.assertThat(illustrazioneC.create(req).getRc()).isEqualTo(false);
		
		req.setUrlIllustrazione("pijama.html");
		Assertions.assertThat(illustrazioneC.create(req).getRc()).isEqualTo(false);
		
		req.setDataIllustrazione(LocalDate.MIN);
		Assertions.assertThat(illustrazioneC.create(req).getRc()).isEqualTo(false);
		
		req.setCategoria("illustrazione");
		Assertions.assertThat(illustrazioneC.create(req).getRc()).isEqualTo(false);
		
		req.setPrezzo(20d);
		Assertions.assertThat(illustrazioneC.create(req).getRc()).isEqualTo(false);
		
		req.setDescrizione("Arte troppo moderna");
		Assertions.assertThat(illustrazioneC.create(req).getRc()).isEqualTo(false);
		
		req.setTitolo("Pane al latte");
		Assertions.assertThat(illustrazioneC.create(req).getRc()).isEqualTo(false);
		
		req.setDimensione("1920x1080");
		Assertions.assertThat(illustrazioneC.create(req).getRc()).isEqualTo(false);
		
		req.setDataCreazione(LocalDate.of(2025, 9, 15));
		Assertions.assertThat(illustrazioneC.create(req).getRc()).isEqualTo(false);
		
		req.setAutore("Alex");
		Assertions.assertThat(illustrazioneC.create(req).getRc()).isEqualTo(false);
		
		req.setImmagine("bread.jpg");
		Assertions.assertThat(illustrazioneC.create(req).getRc()).isEqualTo(false);
		
		req.setIsAI(false);
		Assertions.assertThat(illustrazioneC.create(req).getRc()).isEqualTo(true);
	}
}
