package com.betacom.com;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.com.controller.FotoController;
import com.betacom.com.dto.FotoDTO;
import com.betacom.com.exception.AcademyException;
import com.betacom.com.request.FotoReq;
import com.betacom.com.response.ResponseBase;
import com.betacom.com.response.ResponseList;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FotoControllerTest {
	
	@Autowired FotoController fotoC;
	
	@Test
	@Order(1)
	void createFotoTest() throws AcademyException {
		log.debug("Test Method: createFotoTest()");
		
		FotoReq req = new FotoReq();
		
		req.setPrezzo(150.75); 
		req.setDescrizione("Un'opera d'arte digitale che raffigura un paesaggio astratto."); 
		req.setTitolo("Paesaggio Astratto al Tramonto"); 
		req.setDataCreazione(LocalDate.of(2025, 9, 12)); 
		req.setDimensione("1920x1080"); 
		req.setAutore("Nome Autore"); 
		req.setImmagine("abstract_sunset.jpg"); 
		req.setIsAI(true); 
		req.setCategoria("Arte Digitale"); 
		req.setDevice("Iphone 15 pro MAX");
		req.setWidthResolution(1920);
		req.setHeightResolution(1080);
		
		fotoC.create(req);
		
		ResponseList<FotoDTO> listFoto = fotoC.list();
		
		FotoDTO f = listFoto.getList().stream()
				.filter(e -> e.getId() == 1)
				.findFirst()		
				.orElseThrow(() -> new AssertionError("Foto non trovata"));
		
		Assertions.assertThat(f.getId()).isEqualTo(1);
	}
	
	@Test
	@Order(2)
	void errorCreateFotoTest() throws AcademyException {
		log.debug("Test Method: errorCreateFotoTest()");
		
		FotoReq req = new FotoReq();
		
		req.setPrezzo(150.75); 
		req.setDescrizione("Un'opera d'arte digitale che raffigura un paesaggio astratto."); 
		req.setTitolo("Paesaggio Astratto al Tramonto"); 
		req.setDataCreazione(LocalDate.of(2025, 9, 12)); 
		req.setDimensione("1920x1080"); 
		req.setAutore("Nome Autore"); 
		req.setImmagine("abstract_sunset.jpg"); 
		req.setIsAI(true); 
		// req.setCategoria("Arte Digitale"); -> categoria null
		req.setDevice("Iphone 15 pro MAX");
		req.setWidthResolution(1920);
		req.setHeightResolution(1080);
		
		ResponseBase rB = fotoC.create(req);
		
		
		Assertions.assertThat(rB.getRc()).isEqualTo(false);
	}
	
	@Test
	@Order(3)
	void updateFotoTest() throws AcademyException{
		log.debug("Test Method: updateFotoTest()");
		
		FotoReq req = new FotoReq();
		req.setId(1);
		req.setAutore("Manzoni");
		
		fotoC.update(req);
		
		ResponseList<FotoDTO> listFoto = fotoC.list();
		
		FotoDTO f = listFoto.getList().stream()
				.filter(e -> e.getId() == 1)
				.findFirst()		
				.orElseThrow(() -> new AssertionError("Foto non trovata"));
		
		Assertions.assertThat(f.getOggetto().getAutore()).isEqualTo("Manzoni");
	}
	
	@Test
	@Order(4)
	void errorUpdateFotoTest() throws AcademyException{
		log.debug("Test Method: errorUpdateFotoTest()");
		
		FotoReq req = new FotoReq();
		req.setId(2);
		req.setAutore("Manzoni");
		
		ResponseBase rB = fotoC.update(req);
		
		
		Assertions.assertThat(rB.getRc()).isEqualTo(false);
	}
	
	@Test
	@Order(5)
	void deleteFotoTest() throws AcademyException{
		log.debug("Test Method: deleteFotoTest()");
		
		FotoReq req = new FotoReq();
		req.setId(1);
		
		fotoC.delete(req);
		
		ResponseList<FotoDTO> listFoto = fotoC.list();
		
		Assertions.assertThat(listFoto.getList().size()).isEqualTo(0);
		
	}
	
	@Test
	@Order(5)
	void errorDeleteFotoTest() throws AcademyException{
		log.debug("Test Method: errorDeleteFotoTest()");
		
		FotoReq req = new FotoReq();
		req.setId(1);
		

		ResponseBase rB = fotoC.delete(req);
		
		
		Assertions.assertThat(rB.getRc()).isEqualTo(false);
	
	}

}
