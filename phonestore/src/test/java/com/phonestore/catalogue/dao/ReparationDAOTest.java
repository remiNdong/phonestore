package com.phonestore.catalogue.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import com.phonestore.catalogue.domain.Reparation;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test") // active application-test.properties en PLUS de application.properties
@Import({ TestDBConfig.class })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // use mysql, not an embedded DB
public class ReparationDAOTest {
	

	@Autowired
	ReparationDAO reparationDAO;
	
    @Autowired
    EntityManager entityManager; // to clear cache during tests...



	/**
	 * tests if findAll returns empty when no reparation
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/empty.sql")
	public void findAllEmpty() throws Exception {
		List<Reparation> list = reparationDAO.findAll();

		assertTrue(list.isEmpty(), "With no reparation, findAll should return an empty list");
	}
	

	/**
	 * tests if findAll returns the right number of Reparation
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/catalogue/reparation/loadReparation.sql")
	public void findAll() throws Exception {
		List<Reparation> list = reparationDAO.findAll();

		assertEquals(6, list.size());
	}
	
	/**
	 * tests if findById returns the right  Reparation
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/catalogue/reparation/loadReparation.sql")
	public void findById() throws Exception {
	
		Optional<Reparation> reparation=reparationDAO.findById(1L);
		assertEquals(reparation.get().getNom(), "remplacement écran");
	}
	
	/**
	 * tests if findById on a Id non existant returns empty
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/catalogue/reparation/loadReparation.sql")
	public void findByIdNonExist() throws Exception {
	
		Optional<Reparation> reparation=reparationDAO.findById(7L);
		assertTrue(reparation.isEmpty());
	}
	
	/**
	 * tests if findByReparationNonPratiquees returns the right number of Reparation
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/catalogue/association/loadAssociations.sql")
	public void findByReparationNonPratiquees() throws Exception {
		List<Reparation> list = reparationDAO.findReparationNonPratiquees(1L);

		assertEquals(4, list.size());
	}
	

}
