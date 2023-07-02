package com.phonestore;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;




@SpringBootTest
@ActiveProfiles("test")
class PhonestoreApplicationTests {
	

	
	@Test
	void contextLoads() {
	System.out.println("Démarrage des tests");
		
	}

}
