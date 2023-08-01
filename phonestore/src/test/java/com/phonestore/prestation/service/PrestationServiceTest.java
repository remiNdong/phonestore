package com.phonestore.prestation.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.phonestore.TestDBConfig;
import com.phonestore.prestation.dto.PrestationDTO;
import com.phonestore.prestation.service.PrestationServiceImpl;

import jakarta.persistence.EntityManager;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles({ "test" }) // active application-test.properties en PLUS de application.properties
public class PrestationServiceTest {

	@Autowired
	EntityManager entityManager; // to clear cache during tests.

	@Autowired
	PrestationService prestationService;

	/**
	 * tests if findPrestationByIdentifiantUsager returns the good List of prestations
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/catalogue/prestation/loadPrestation.sql")
	public void findPrestationByIdentifiantUsager() throws Exception {

		List<PrestationDTO> prestations = prestationService.findPrestationByIdentifiantUsager("client@hotmail.fr");

		assertEquals(1, prestations.size());

	}
	
	
	
	/**
	 * tests if findPrestationByNomUsager returns the good  of prestation
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/catalogue/prestation/loadPrestation.sql")
	public void findPrestationById() throws Exception {

		PrestationDTO prestation = prestationService.findPrestationById(1L).get();

		assertEquals("client@hotmail.fr", prestation.getIdentifiantUsager());
		assertEquals("iphone_14",prestation.getNomModele());
		assertEquals("APPLE",prestation.getNomMarque());
		assertEquals("remplacement batterie",prestation.getNomReparation());
		assertEquals("APPLEIPHONE14",prestation.getNumeroSerie());
		assertEquals("CREE", prestation.getStatus());
		System.out.println("Date création :"+prestation.getDateCreation());
		System.out.println("Id association :"+prestation.getIdAssociation());
		System.out.println("Prix association :"+prestation.getPrix());
		

	}


}
