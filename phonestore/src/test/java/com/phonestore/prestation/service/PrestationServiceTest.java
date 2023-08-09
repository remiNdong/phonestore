package com.phonestore.prestation.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import com.phonestore.DefaultContent;
import com.phonestore.TestDBConfig;
import com.phonestore.administration.exception.UserNonUsagerException;
import com.phonestore.catalogue.dto.AssociationmodelereparationDTO;
import com.phonestore.catalogue.dto.ModeletelephoneCreationDTO;
import com.phonestore.catalogue.dto.ModeletelephoneDTO;
import com.phonestore.catalogue.exception.AssociationmodeletelephonereparationNonExistanteException;
import com.phonestore.catalogue.exception.IdModeleNonExistantException;
import com.phonestore.prestation.dto.PrestationDTO;
import com.phonestore.prestation.exception.UserNonExistantException;
import com.phonestore.prestation.service.PrestationServiceImpl;

import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintViolationException;

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
	
	/**
	 * tests if createPrestation functions
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/catalogue/prestation/loadPrestation.sql")
public void testCreatePrestation() {

		
		
		Long idPrestation=prestationService.createPrestation(DefaultContent.defaultPrestationCreationDTO());
		PrestationDTO newPrestationDTO=prestationService.findPrestationById(idPrestation).get();
		assertTrue(prestationService.findPrestationByIdentifiantUsager("client@hotmail.fr").size()==2);
		assertTrue(newPrestationDTO.getIdentifiantUsager().equals("client@hotmail.fr"));
		assertTrue(newPrestationDTO.getIdAssociation()==2L);
		assertTrue(newPrestationDTO.getPrix()==328);
	
		assertTrue(newPrestationDTO.getNumeroSerie().equals("IPHONEXX14"));
		assertTrue(newPrestationDTO.getNomMarque().equals("APPLE"));
		assertTrue(newPrestationDTO.getNomModele().equals("iphone_14"));
		
		
		

	}


	/**
	 * tests if createPrestation throws the right Exception
	 * 
	 * @throws Exception
	 */
@Test
@Sql("/testsql/catalogue/prestation/loadPrestation.sql")
public void testCreateModeleUserFaux() {

	
	assertThrows(UserNonExistantException.class, ()->prestationService.createPrestation(DefaultContent.defaultPrestationCreationDTOUserFaux()));
	

}


/**
 * tests if createPrestation throws the right Exception
 * 
 * @throws Exception
 */
@Test
@Sql("/testsql/catalogue/prestation/loadPrestation.sql")
public void testCreateModeleUserNonUsager() {


assertThrows(UserNonUsagerException.class, ()->prestationService.createPrestation(DefaultContent.defaultPrestationCreationDTOUserNonUsager()));


}


/**
 * tests if createPrestation throws the right Exception
 * 
 * @throws Exception
 */
@Test
@Sql("/testsql/catalogue/prestation/loadPrestation.sql")
public void testCreateModeleModeleFaux() {


assertThrows(IdModeleNonExistantException.class, ()->prestationService.createPrestation(DefaultContent.defaultPrestationCreationDTOModeleFaux()));


}

/**
 * tests if createPrestation throws the right Exception
 * 
 * @throws Exception
 */
@Test
@Sql("/testsql/catalogue/prestation/loadPrestation.sql")
public void testCreateModeleAssociationFausse() {


assertThrows(AssociationmodeletelephonereparationNonExistanteException.class, ()->prestationService.createPrestation(DefaultContent.defaultPrestationCreationDTOAssociationFausse()));


}


}
