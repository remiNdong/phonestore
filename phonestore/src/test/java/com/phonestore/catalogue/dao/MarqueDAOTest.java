package com.phonestore.catalogue.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.phonestore.TestDBConfig;
import com.phonestore.catalogue.domain.Marque;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test") // active application-test.properties en PLUS de application.properties
@Import({ TestDBConfig.class })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // use mysql, not an embedded DB
class MarqueDAOTest {


	
	@Autowired
	MarqueDAO marqueDAO;
	
    @Autowired
    EntityManager entityManager; // to clear cache during tests...



	/**
	 * tests if findAll returns empty when no marque
	 * 
	 * @throws Exception
	 
	@Test
	@Sql("/testsql/empty.sql")
	public void findAllEmpty() throws Exception {
		List<Marque> list = marqueDAO.findAll();

		//assertTrue(list.isEmpty(), "With no marque, findAll should return an empty list");
	}
	

	
	 * tests if findAll returns the right number of Marque
	 * 
	 * @throws Exception
	 
	@Test
	@Sql("/testsql/catalogue/marque/loadMarques.sql")
	public void findAll() throws Exception {
		List<Marque> list = marqueDAO.findAll();

		//assertEquals(2, list.size());
	}
	
	
	 * tests if findById returns the right  Marque
	 * 
	 * @throws Exception
	 
	@Test
	@Sql("/testsql/catalogue/marque/loadMarques.sql")
	public void findById() throws Exception {
	
		Optional<Marque> marque=marqueDAO.findById(1L);
		//assertEquals(marque.get().getNom(), "APPLE");
	}
	
	
	 * tests if findById on a Id non existant returns empty
	 * 
	 * @throws Exception
	 
	@Test
	@Sql("/testsql/catalogue/marque/loadMarques.sql")
	public void findByIdNonExist() throws Exception {
	
		Optional<Marque> marque=marqueDAO.findById(3L);
		//assertTrue(marque.isEmpty());
	}
	
	**/
	


}
