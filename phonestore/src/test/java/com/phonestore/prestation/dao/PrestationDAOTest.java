package com.phonestore.prestation.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.phonestore.TestDBConfig;
import com.phonestore.administration.dao.UserDAO;
import com.phonestore.administration.domain.User;
import com.phonestore.prestation.domain.Prestation;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test") // active application-test.properties en PLUS de application.properties
@Import({ TestDBConfig.class })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // use mysql, not an embedded DB
public class PrestationDAOTest {
	
	  @Autowired
	    EntityManager entityManager; // to clear cache during tests.
	  
	  @Autowired
	  PrestationDAO prestationDAO;
	  
	  @Autowired
	  UserDAO userDAO;
	  
		/**
		 * tests if findByUser returns the good List of prestations
		 * 
		 * @throws Exception
		 */
		@Test
		@Sql("/testsql/catalogue/prestation/loadPrestation.sql")
		public void findByUser() throws Exception {
			
			User user=userDAO.findById(3L).get();
			
			List<Prestation> prestations=prestationDAO.findByUsager(user);
			
			assertEquals(1,prestations.size());
			
			 user=userDAO.findById(4L).get();
				
			 prestations=prestationDAO.findByUsager(user);
				
				assertEquals(0,prestations.size());
			
			
		}

}
