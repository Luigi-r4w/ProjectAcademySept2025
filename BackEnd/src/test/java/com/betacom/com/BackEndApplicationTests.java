package com.betacom.com;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@Suite
@SelectClasses({
	UtenteServicesTest.class,
	DisegnoServicesTest.class,
	FotoControllerTest.class,
	IllustrazioneServicesTest.class
})
@SpringBootTest
class BackEndApplicationTests {

	@Test
	void contextLoads() {
	}

}
