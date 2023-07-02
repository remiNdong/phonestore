package com.phonestore.catalogue.dao;

import static org.junit.jupiter.api.Assertions.*;

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
import com.phonestore.catalogue.domain.Modeletelephone;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test") // active application-test.properties en PLUS de application.properties
@Import({ TestDBConfig.class })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // use mysql, not an embedded DB
class ModeletelephoneDAOTest {

	@Autowired
	MarqueDAO marqueDAO;
	
	@Autowired
	ModeletelephoneDAO modeletelephoneDAO;
	
    @Autowired
    EntityManager entityManager; // to clear cache during tests...
    
    /**
	 * tests if findAll returns empty when no modeletelephone
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/empty.sql")
	public void findAllEmpty() throws Exception {
		List<Modeletelephone> list = modeletelephoneDAO.findAll();

		assertTrue(list.isEmpty(), "With no modeletelephone, findAll should return an empty list");
	}
	
	
	/**
	 * tests if findAll returns the right number of Modeletelephone
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void findAll() throws Exception {
		List<Modeletelephone> list = modeletelephoneDAO.findAll();

		assertEquals(5, list.size());
	}

	/**
	 * tests if findById returns the right  Modeletelephone
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void findById() throws Exception {
		
		Optional<Marque> optMarque=marqueDAO.findById(1L);
		Marque marque=optMarque.get();
		
	
		Optional<Modeletelephone> modele=modeletelephoneDAO.findById(1L);
		assertEquals(modele.get().getTailleEcran(), 6.1);
		assertEquals(modele.get().getCapaciteStockage(), 128);
		assertEquals(modele.get().getReference(), "iphone_14");
		assertEquals(modele.get().getMarque(), marque);
		assertEquals(modele.get().getImagePath(), "iphone_14.jpg");
		assertEquals(modele.get().getPrix(), 869);
		
	}
	
	
	/**
	 * tests if findById on a Id non existant returns empty
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void findByIdNonExist() throws Exception {
	
		Optional<Modeletelephone> modele=modeletelephoneDAO.findById(6L);
		assertTrue(modele.isEmpty());
	}
	
	/**
	 * tests if findByTailleEcran returns the right  Modeletelephone
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void findByTailleEcran() throws Exception {
	
		Optional<Modeletelephone> optmodele=modeletelephoneDAO.findById(1L);
		Modeletelephone modele1=optmodele.get();
		 optmodele=modeletelephoneDAO.findById(2L);
		Modeletelephone modele2=optmodele.get();
		 optmodele=modeletelephoneDAO.findById(5L);
		Modeletelephone modele3=optmodele.get();
		
		
		List<Modeletelephone> list=modeletelephoneDAO.findByTailleEcran(6.1);
		assertEquals(list.size(),3);
		assertTrue(list.contains(modele1));
		assertTrue(list.contains(modele2));
		assertTrue(list.contains(modele3));
		
		
	}
	
	/**
	 * tests if findByCapaciteStockage returns the right  Modeletelephone
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void findByCapaciteStockage() throws Exception {
	
		Optional<Modeletelephone> optmodele=modeletelephoneDAO.findById(1L);
		Modeletelephone modele1=optmodele.get();
		 optmodele=modeletelephoneDAO.findById(2L);
		Modeletelephone modele2=optmodele.get();
		 optmodele=modeletelephoneDAO.findById(3L);
		Modeletelephone modele3=optmodele.get();
		
		
		List<Modeletelephone> list=modeletelephoneDAO.findByCapaciteStockage(128);
		assertEquals(list.size(),3);
		assertTrue(list.contains(modele1));
		assertTrue(list.contains(modele2));
		assertTrue(list.contains(modele3));
		
		
	}
	
	
	
}
