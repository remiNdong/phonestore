package com.phonestore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.phonestore.administration.domain.User;
import com.phonestore.administration.service.UserService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class PhonestoreApplication {

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(PhonestoreApplication.class, args);

	}

	@Bean
	BCryptPasswordEncoder getBCE() {
		return new BCryptPasswordEncoder();
	}

	@PostConstruct
	void init_users() {

		User user = userService.findUserByUsername("phonetastik@yahoo.com");

		if (user == null) {
			// ajouter les users
			userService.saveUser(new User(null, "phonetastik@yahoo.com", "Rachid", "Marzac", "0650426820",
					"SaintOmer62500!", null, null));

			// ajouter les rôles aux users

			userService.addRoleToUser("phonetastik@yahoo.com", "ADMIN");

		}
	}

}
