package com.betacom.com;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.com.controller.DisegnoController;
import com.betacom.com.exception.AcademyException;
import com.betacom.com.request.DisegnoReq;
import com.betacom.com.response.ResponseBase;
import com.betacom.com.response.ResponseList;
import com.betacom.com.dto.DisegnoDTO;
import com.betacom.com.response.ResponseObject;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DisegnoServicesTest {
	@Autowired
	DisegnoController disC;
	
	@Test
	@Order(1)
	public void insertDisegnoTest() throws AcademyException {
		DisegnoReq d = new DisegnoReq();
		
		d.setAutore("ProvaAutore1");
		d.setCategoria("ProvaCategoria1");
		d.setDataCreazione(LocalDate.now());
		d.setDescrizione("ProvaDescrizione1");
		d.setDimensione("ProvaDimensione1");
		d.setImmagine("ProvaImmagine1");
		d.setIsAI(true);
		d.setPrezzo(0.1);
		d.setSupporto("ProvaSupporto1");
		d.setTecnica("ProvaTecnica1");
		d.setTitolo("ProvaTitolo1");
		
		ResponseBase r = disC.create(d);
		
		Assertions.assertThat(r.getRc()).isEqualTo(true);
	}
	
	@Test
	@Order(2)
	public void updateDisegnoTest() throws AcademyException{
		DisegnoReq d = new DisegnoReq();
		
		d.setId(1);
		d.setAutore("ProvaAutore2");
		d.setCategoria("ProvaCategoria2");
		d.setDataCreazione(LocalDate.now());
		d.setDescrizione("ProvaDescrizione2");
		d.setDimensione("ProvaDimensione2");
		d.setImmagine("ProvaImmagine2");
		d.setIsAI(false);
		d.setPrezzo(0.2);
		d.setSupporto("ProvaSupporto2");
		d.setTecnica("ProvaTecnica2");
		d.setTitolo("ProvaTitolo2");
		
		
		ResponseBase r = disC.update(d);
		
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		
		ResponseObject<DisegnoDTO> rO = disC.findById(1);
		
		Assertions.assertThat(rO.getRc()).isEqualTo(true);
		
		Assertions.assertThat(rO.getDati().getOggetto().getAutore()).isEqualTo("ProvaAutore2");
		Assertions.assertThat(rO.getDati().getOggetto().getCategoria()).isEqualTo("ProvaCategoria2");
		Assertions.assertThat(rO.getDati().getOggetto().getDescrizione()).isEqualTo("ProvaDescrizione2");
		Assertions.assertThat(rO.getDati().getOggetto().getDimensione()).isEqualTo("ProvaDimensione2");
		Assertions.assertThat(rO.getDati().getOggetto().getImmagine()).isEqualTo("ProvaImmagine2");
		Assertions.assertThat(rO.getDati().getOggetto().getIsAI()).isEqualTo(false);
		Assertions.assertThat(rO.getDati().getOggetto().getPrezzo()).isEqualTo(0.2);
		Assertions.assertThat(rO.getDati().getSupporto()).isEqualTo("ProvaSupporto2");
		Assertions.assertThat(rO.getDati().getTecnica()).isEqualTo("ProvaTecnica2");
		Assertions.assertThat(rO.getDati().getOggetto().getTitolo()).isEqualTo("ProvaTitolo2");
		
	}
	
	@Test
	@Order(3)
	public void listAllDisegnoTest() throws AcademyException {
		ResponseList<DisegnoDTO> r = disC.listAll();
		
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		
		Assertions.assertThat(r.getList().getFirst().getSupporto()).isEqualTo("ProvaSupporto2");
	}
	
	@Test
	@Order(4)
	public void deleteDisegnoTest() throws AcademyException{
		DisegnoReq d = new DisegnoReq();
		
		d.setId(1);
		
		ResponseBase r = disC.delete(d);
		
		Assertions.assertThat(r.getRc()).isEqualTo(true);
	}
	
	@Test
	@Order(5)
	public void insertDisegnoTestError() throws AcademyException {
		DisegnoReq d = new DisegnoReq();
		
		d.setAutore("ProvaAutore1");
		d.setCategoria("ProvaCategoriaaa1");
		d.setDataCreazione(LocalDate.now());
		d.setDescrizione("ProvaDescrizione1");
		d.setDimensione("ProvaDimensione1");
		d.setImmagine("ProvaImmagine1");
		d.setIsAI(true);
		d.setPrezzo(0.1);
		d.setSupporto("ProvaSupporto1");
		//d.setTecnica("ProvaTecnica1");
		d.setTitolo("ProvaTitolo1");
		
		ResponseBase r = disC.create(d);
		
		Assertions.assertThat(r.getRc()).isEqualTo(false);
		Assertions.assertThat(r.getMsg()).isEqualTo("Tecnica obbligatoria");
	}
	
	@Test
	@Order(6)
	public void insertDisegno2TestError() throws AcademyException {
		DisegnoReq d = new DisegnoReq();
		
		d.setAutore("ProvaAutore1");
		d.setCategoria("ProvaCategoriaaa1");
		d.setDataCreazione(LocalDate.now());
		d.setDescrizione("ProvaDescrizione1");
		d.setDimensione("ProvaDimensione1");
		d.setImmagine("ProvaImmagine1");
		d.setIsAI(true);
		d.setPrezzo(0.1);
		//d.setSupporto("ProvaSupporto1");
		d.setTecnica("ProvaTecnica1");
		d.setTitolo("ProvaTitolo1");
		
		ResponseBase r = disC.create(d);
		
		Assertions.assertThat(r.getRc()).isEqualTo(false);
		Assertions.assertThat(r.getMsg()).isEqualTo("Supporto obbligatorio");
	}
	
	@Test
	@Order(7)
	public void updateDisegnoTestError() throws AcademyException {
		DisegnoReq d = new DisegnoReq();
		
		d.setId(99);
		
		ResponseBase r = disC.update(d);
		Assertions.assertThat(r.getRc()).isEqualTo(false);
		Assertions.assertThat(r.getMsg()).isEqualTo("Disegno non trovato nel database");
	}
	
	@Test
	@Order(8)
	public void deleteDisegnoTestError() throws AcademyException {
		DisegnoReq d = new DisegnoReq();
		
		d.setId(99);
		
		ResponseBase r = disC.delete(d);
		Assertions.assertThat(r.getRc()).isEqualTo(false);
		Assertions.assertThat(r.getMsg()).isEqualTo("Disegno non trovato nel database");
	}
	
	@Test
	@Order(9)
	public void findByIdDisegnoTestError() throws AcademyException {
		ResponseBase r = disC.findById(99);
		Assertions.assertThat(r.getRc()).isEqualTo(false);
		Assertions.assertThat(r.getMsg()).isEqualTo("Disegno non trovato nel database");
	}
	
	
}
