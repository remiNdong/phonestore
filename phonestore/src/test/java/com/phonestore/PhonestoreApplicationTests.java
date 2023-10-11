package com.phonestore;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import com.phonestore.administration.service.UserService;




@SpringBootTest
@ActiveProfiles("test")
class PhonestoreApplicationTests {
	

	@Autowired
	UserService userService;

	@Bean
	BCryptPasswordEncoder getBCE() {
		return new BCryptPasswordEncoder();
	}
	
	@Test
	void contextLoads() {
	System.out.println("Démarrage des tests");
		
	}

}
