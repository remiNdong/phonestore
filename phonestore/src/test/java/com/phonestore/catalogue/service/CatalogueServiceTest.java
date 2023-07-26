package com.phonestore.catalogue.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.phonestore.DefaultContent;
import com.phonestore.catalogue.dao.MarqueDAO;
import com.phonestore.catalogue.domain.Associationmodelereparation;
import com.phonestore.catalogue.dto.AssociationmodelereparationDTO;
import com.phonestore.catalogue.dto.MarqueDTO;
import com.phonestore.catalogue.dto.ModeletelephoneCreationDTO;
import com.phonestore.catalogue.dto.ModeletelephoneDTO;
import com.phonestore.catalogue.dto.ModeletelephoneUpdatedDTO;
import com.phonestore.catalogue.dto.ReparationDTO;
import com.phonestore.catalogue.exception.IdMarqueNonExistanteException;
import com.phonestore.catalogue.exception.IdModeleNonExistantException;
import com.phonestore.catalogue.exception.ReferenceModeleExistanteException;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles({ "test" }) // active application-test.properties en PLUS de application.properties
public class CatalogueServiceTest {

	@Autowired
	CatalogueService catalogueService;
	
	@Autowired 
	MarqueDAO marqueDAO;
	
	

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
	public void testFindReparationsEmpty() {
		List<ReparationDTO> res = catalogueService.findReparations();
		assertTrue(res.isEmpty(), "Reparations : Sould return empty when not found");
	}

	@Test
	@Sql("/testsql/catalogue/reparation/loadReparation.sql")
	public void testFindReparations() {
		List<ReparationDTO> res = catalogueService.findReparations();
		assertTrue(res.size() == 6);
	}
	
	@Test
	@Sql("/testsql/catalogue/association/createTablesNoInserts.sql")
	public void testFindAssociationsEmpty() {
		List<AssociationmodelereparationDTO> res = catalogueService.findAssociationmodelereparationByModele(1L);
		assertTrue(res.isEmpty(), "Reparations : Sould return empty when not found");
	}

	@Test
	@Sql("/testsql/catalogue/association/loadAssociations.sql")
	public void testFindAssociations() {
		
		
		List<AssociationmodelereparationDTO> res = catalogueService.findAssociationmodelereparationByModele(1L);
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
		assertTrue(modeleDTO.getMarqueDTO().getId() == 1);

	}

	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testFindModeleByIdNonExist() {

		
		assertThrows(IdModeleNonExistantException.class, ()->catalogueService.findModele(6L));

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

		
		assertThrows(IdMarqueNonExistanteException.class, ()->catalogueService.findModeletelephonesByMarque(3L));

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
	public void testFindModeletelephonesByReference() {

		List<ModeletelephoneDTO> res = catalogueService.findModeletelephoneByReference("iphone_14");
		assertTrue(res.size() == 1);
		assertTrue(res.get(0).getId() == 1);

	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testFindModeletelephonesByPrix() {

		List<ModeletelephoneDTO> res = catalogueService.findModeletelephonesByPrix(100, 900);
		assertTrue(res.size() == 2);
		assertTrue(res.get(0).getId() == 5);
		assertTrue(res.get(1).getId() == 1); // on verifie que l'ordre de prix croissant par defaut est respecté

	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testFindModeletelephonesByPrixNone() {

		List<ModeletelephoneDTO> res = catalogueService.findModeletelephonesByPrix(2000, 3000);
		assertTrue(res.size() == 0);
		

	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testFindModeletelephonesByTailleEcranRange() {

		List<ModeletelephoneDTO> res = catalogueService.findModeletelephonesByTailleEcranRange(6, 7);
		assertTrue(res.size() == 5);
		res = catalogueService.findModeletelephonesByTailleEcranRange(0, 6);
		assertTrue(res.size() == 0);
		res = catalogueService.findModeletelephonesByTailleEcranRange(7, 10);
		assertTrue(res.size() == 0);
		

	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void FindModeletelephonesByCapaciteStockageNonExist() {
		
	

		List<ModeletelephoneDTO> res = catalogueService.findModeletelephonesByCapaciteStockage(1028);
		assertTrue(res.isEmpty());
		

	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testCreateModele() {

		
		
		Long idModele=catalogueService.createModele(DefaultContent.defaultModeleCreationDTO());
		ModeletelephoneDTO newModeleDTO=catalogueService.findModele(idModele).get();
		assertTrue(newModeleDTO.getReference().equals("iphone_12"));
		assertTrue(newModeleDTO.getImagePath().equals("iphone_12.jpg"));
		assertTrue(newModeleDTO.getPrix()==809);
		assertTrue(newModeleDTO.getTailleEcran()==6.1);
		assertTrue(newModeleDTO.getCapaciteStockage()==128);
		assertTrue(newModeleDTO.getMarqueDTO().getId()==1L);
		assertTrue(newModeleDTO.getMarqueDTO().getNom().equals("APPLE"));
		

	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testCreateModeleLowerCase() {

		ModeletelephoneCreationDTO modeletelephoneCreationDTO=DefaultContent.defaultModeleCreationDTO();
		modeletelephoneCreationDTO.setReference("IPHONE_12");
		Long idModele=catalogueService.createModele(modeletelephoneCreationDTO);
		ModeletelephoneDTO newModeleDTO=catalogueService.findModele(idModele).get();
		assertTrue(newModeleDTO.getReference().equals("iphone_12"));
		assertTrue(newModeleDTO.getImagePath().equals("iphone_12.jpg"));
		assertTrue(newModeleDTO.getPrix()==809);
		assertTrue(newModeleDTO.getTailleEcran()==6.1);
		assertTrue(newModeleDTO.getCapaciteStockage()==128);
		assertTrue(newModeleDTO.getMarqueDTO().getId()==1L);
		assertTrue(newModeleDTO.getMarqueDTO().getNom().equals("APPLE"));
		

	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testCreateModeleDejaExistant() {

		
		catalogueService.createModele(DefaultContent.defaultModeleCreationDTO());
		assertThrows(ReferenceModeleExistanteException.class, ()->catalogueService.createModele(DefaultContent.defaultModeleCreationDTO()));
		
		
	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testCreateModeleReferenceFausse() {

		ModeletelephoneCreationDTO modeleFaux=new ModeletelephoneCreationDTO(0L,"", 809, 6.1, 128, DefaultContent.defaultMarqueDTO());
		
		assertThrows(ConstraintViolationException.class, ()->catalogueService.createModele(modeleFaux));
		
		
	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testCreateModelePrixFaux() {

		ModeletelephoneCreationDTO modeleFaux=new ModeletelephoneCreationDTO(0L,"iphone_12", 0, 6.1, 128, DefaultContent.defaultMarqueDTO());
		
		assertThrows(ConstraintViolationException.class, ()->catalogueService.createModele(modeleFaux));
		
		
	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testCreateModeleTailleEcranFaux() {

		ModeletelephoneCreationDTO modeleFaux=new ModeletelephoneCreationDTO(0L,"iphone_12", 869, 0, 128, DefaultContent.defaultMarqueDTO());
		
		assertThrows(ConstraintViolationException.class, ()->catalogueService.createModele(modeleFaux));
		
		
	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testCreateModeleMemoireFausse() {

		ModeletelephoneCreationDTO modeleFaux=new ModeletelephoneCreationDTO(0L,"iphone_12", 869, 6.1, 0, DefaultContent.defaultMarqueDTO());
		
		assertThrows(ConstraintViolationException.class, ()->catalogueService.createModele(modeleFaux));
		
		
	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testCreateModeleMarqueFausse() {

		ModeletelephoneCreationDTO modeleFaux=new ModeletelephoneCreationDTO(0L,"iphone_12", 869, 6.1, 128, null);
		
		assertThrows(ConstraintViolationException.class, ()->catalogueService.createModele(modeleFaux));
		
		
	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testCreateModeleIdMarqueNonExistante() {

		ModeletelephoneCreationDTO modeleFaux=new ModeletelephoneCreationDTO(0L,"iphone_12", 869, 6.1, 128, DefaultContent.defaultMarqueDTOFalse());
		
		assertThrows(IdMarqueNonExistanteException.class, ()->catalogueService.createModele(modeleFaux));
		
		
	}
	
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testUpdateModele() {

		
		
		Long idModele=catalogueService.updateModele(DefaultContent.defaultModeleUpdatedDTO());
		ModeletelephoneDTO newModeleDTO=catalogueService.findModele(idModele).get();
		assertTrue(newModeleDTO.getReference().equals("iphone_se"));
		assertTrue(newModeleDTO.getImagePath().equals("iphone_se.jpg"));
		assertTrue(newModeleDTO.getPrix()==559);
		assertTrue(newModeleDTO.getTailleEcran()==4.7);
		assertTrue(newModeleDTO.getCapaciteStockage()==64);
		assertTrue(newModeleDTO.getMarqueDTO().getId()==1L);
		assertTrue(newModeleDTO.getMarqueDTO().getNom().equals("APPLE"));
		

	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testUpdateModeleSansModifRef() {

		ModeletelephoneUpdatedDTO newModele=DefaultContent.defaultModeleUpdatedDTO();
		newModele.setReference("iphone_14");
		ModeletelephoneDTO modeleUpdated=catalogueService.findModele(newModele.getId()).get();
		assertTrue(modeleUpdated.getReference().equals("iphone_14"));
		assertTrue(modeleUpdated.getCapaciteStockage()==128);
		
		//on veut juste verifier qu'une mise a jour sans changement de reference fonctionne
		
	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testUpdateModeleDejaExistant() {

		ModeletelephoneUpdatedDTO newModele=DefaultContent.defaultModeleUpdatedDTO();
		newModele.setReference("iphone_14");
		newModele.setId(2L);
		assertThrows(ReferenceModeleExistanteException.class, ()->catalogueService.updateModele(newModele));
		
		//on veut verifier que la mise a jour d'un autre modele en metant une reference deja existante est interdite
		
		
	}
	
	@Test
	@Sql("/testsql/catalogue/modeletelephone/loadModeles.sql")
	public void testUpdateModeleMarqueFausse() {

		
		ModeletelephoneUpdatedDTO newModele=DefaultContent.defaultModeleUpdatedDTO();
		newModele.setMarqueDTO(DefaultContent.defaultMarqueDTOFalse());
		assertThrows(IdMarqueNonExistanteException.class, ()->catalogueService.updateModele(newModele));
		
		
	}
	

	
	
	
	

}
