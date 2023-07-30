package com.phonestore.catalogue.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.phonestore.TestDBConfig;
import com.phonestore.catalogue.domain.Associationmodelereparation;
import com.phonestore.catalogue.domain.Modeletelephone;
import com.phonestore.catalogue.domain.Reparation;
import com.phonestore.catalogue.dto.AssociationmodelereparationDTO;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test") // active application-test.properties en PLUS de application.properties
@Import({ TestDBConfig.class })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // use mysql, not an embedded DB
public class AssociationmodelereparationTest {

	@Autowired
	AssociationmodelereparationDAO associationmodelereparationDAO;

	@Autowired
	EntityManager entityManager; // to clear cache during tests...

	@Autowired
	ModeletelephoneDAO modeletelephoneDAO;

	@Autowired
	ReparationDAO reparationDAO;

	@Test
	@Sql("/testsql/catalogue/association/createTablesNoInserts.sql")
	public void testFindAssociationsEmpty() {

		Modeletelephone modele = modeletelephoneDAO.findById(1L).get();

		List<Associationmodelereparation> res = associationmodelereparationDAO.findByModeletelephone(modele);
		assertTrue(res.isEmpty(), "Associations : Sould return empty when not found");
	}

	@Test
	@Sql("/testsql/catalogue/association/loadAssociations.sql")
	public void testFindAssociations() {

		Modeletelephone modele = modeletelephoneDAO.findById(1L).get();

		List<Associationmodelereparation> res = associationmodelereparationDAO.findByModeletelephone(modele);
		assertEquals(2, res.size());
	}

	@Test
	@Sql("/testsql/catalogue/association/loadAssociations.sql")
	public void testFindAssociationsByModeleAndReparation() {

		Modeletelephone modele = modeletelephoneDAO.findById(1L).get();
		Reparation reparation = reparationDAO.findById(2L).get();

		List<Associationmodelereparation> res = associationmodelereparationDAO
				.findByModeletelephoneAndReparation(modele, reparation);
		assertEquals(1, res.size());
	}
	
	@Test
	@Sql("/testsql/catalogue/association/loadAssociations.sql")
	public void testFindAssociationsById() {

		

		Associationmodelereparation association = associationmodelereparationDAO.findById(1L).get();
		
		assertEquals(1L, association.getModeletelephone().getId(), "Id modeletelephone faux");
		assertEquals(2L, association.getReparation().getId(), "Id reparation faux");
		assertEquals(130, association.getPrix(), "prix faux");
		assertEquals(1, association.getPraticable(), "praticable faux");
		
		
	}

}
