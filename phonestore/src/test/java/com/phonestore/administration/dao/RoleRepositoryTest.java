package com.phonestore.administration.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.phonestore.TestDBConfig;
import com.phonestore.administration.domain.Role;

import jakarta.persistence.EntityManager;
@DataJpaTest
@ActiveProfiles("test") // active application-test.properties en PLUS de application.properties
@Import({ TestDBConfig.class })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // use mysql, not an embedded DB
public class RoleRepositoryTest {
	
	
	@Autowired
	RoleDAO roleRepository;
	
	@Autowired
	UserDAO userRepository;
	
	
	 @Autowired
	    EntityManager entityManager; // to clear cache during tests...


		/**
		 * tests if findAll returns all the roles
		 * 
		 * @throws Exception
		 */
		@Test
		@Sql("/testsql/loadUser.sql")
		public void findAllRole() throws Exception {
			List<Role> list = roleRepository.findAll();

			assertEquals(3,list.size());
		}
		
		/**
		 * tests if findById returns the goodRole
		 * 
		 * @throws Exception
		 */
		@Test
		@Sql("/testsql/loadUser.sql")
		public void findById() throws Exception {
			Role admin = roleRepository.findById(1L).get();
				assertEquals("ADMIN",admin.getRole());
				
				Role emp = roleRepository.findById(2L).get();
				assertEquals("EMP",emp.getRole());
				
				Role user = roleRepository.findById(3L).get();
				assertEquals("USER",user.getRole());
		}
		


}
