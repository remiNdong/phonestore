package com.phonestore.catalogue.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.phonestore.catalogue.dto.MarqueDTO;
import com.phonestore.catalogue.dto.ModeletelephoneDTO;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles({ "test" }) // active application-test.properties en PLUS de application.properties
public class CatalogueServiceTest {

	@Autowired
	CatalogueService catalogueService;

	@Test
	@Sql("/testsql/empty.sql")
	public void testFindMarquesEmpty() {
		List<MarqueDTO> res = catalogueService.findMarques();
		assertTrue(res.isEmpty(), "Marques : Sould return empty when not found");
	}

	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testFindMarques() {
		List<MarqueDTO> res = catalogueService.findMarques();
		assertTrue(res.size() == 2);
	}

	@Test
	@Sql("/testsql/empty.sql")
	public void testFindModelesEmpty() {
		List<ModeletelephoneDTO> res = catalogueService.findModeletelephones();
		assertTrue(res.isEmpty(), "Modeles : Sould return empty when not found");
	}

	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testFindModeles() {
		List<ModeletelephoneDTO> res = catalogueService.findModeletelephones();
		assertTrue(res.size() == 5);
		assertTrue(res.get(0).getId() == 5);
		assertTrue(res.get(4).getId() == 3); // on verifie que l'ordre de prix croissant par defaut est respecté
	}

	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testFindModeleById() {
		Optional<ModeletelephoneDTO> optModeleDTO = catalogueService.findModele(1L);
		ModeletelephoneDTO modeleDTO = optModeleDTO.get();
		assertTrue(modeleDTO.getTailleEcran() == 6.1);
		assertTrue(modeleDTO.getCapaciteStockage() == 128);
		assertTrue(modeleDTO.getImagePath().equals("iphone_14.jpg"));
		assertTrue(modeleDTO.getPrix() == 869);
		assertTrue(modeleDTO.getReference().equals("iphone_14"));
		assertTrue(modeleDTO.getIdMarque() == 1);

	}

	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testFindModeleByIdNonExist() {
		Optional<ModeletelephoneDTO> optModeleDTO = catalogueService.findModele(6L);
		assertTrue(optModeleDTO.isEmpty());

	}

	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testFindModeleByMarque() {

		List<ModeletelephoneDTO> res = catalogueService.findModeletelephonesByMarque(1L);
		assertTrue(res.size() == 3);
		assertTrue(res.get(0).getId() == 1);
		assertTrue(res.get(2).getId() == 3); // on verifie que l'ordre de prix croissant par defaut est respecté

	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testFindModeleByMarqueNonExist() {

		List<ModeletelephoneDTO> res = catalogueService.findModeletelephonesByMarque(3L);
		assertTrue(res.isEmpty());

	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testFindModeletelephonesByTailleEcran() {

		List<ModeletelephoneDTO> res = catalogueService.findModeletelephonesByTailleEcran(6.1);
		assertTrue(res.size() == 3);
		assertTrue(res.get(0).getId() == 5);
		assertTrue(res.get(2).getId() == 2); // on verifie que l'ordre de prix croissant par defaut est respecté

	}
	

	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testFindModeletelephonesByTailleEcranNonExist() {

		List<ModeletelephoneDTO> res = catalogueService.findModeletelephonesByTailleEcran(10);
		assertTrue(res.isEmpty());
		

	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testFindModeletelephonesByCapaciteStockage() {

		List<ModeletelephoneDTO> res = catalogueService.findModeletelephonesByCapaciteStockage(128);
		assertTrue(res.size() == 3);
		assertTrue(res.get(0).getId() == 1);
		assertTrue(res.get(2).getId() == 3); // on verifie que l'ordre de prix croissant par defaut est respecté

	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void FindModeletelephonesByCapaciteStockageNonExist() {

		List<ModeletelephoneDTO> res = catalogueService.findModeletelephonesByCapaciteStockage(1028);
		assertTrue(res.isEmpty());
		

	}
	
	

}
