package com.phonestore.catalogue.restcontrollers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.phonestore.catalogue.dto.MessageDTO;
import com.phonestore.catalogue.dto.ModeletelephoneCreationDTO;
import com.phonestore.catalogue.dto.ModeletelephoneDTO;
import com.phonestore.catalogue.dto.ModeletelephoneUpdatedDTO;
import com.phonestore.catalogue.exception.ReferenceModeleExistanteException;
import com.phonestore.catalogue.service.CatalogueService;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/modeles")
@CrossOrigin
public class ModeleRestController {

	@Autowired
	CatalogueService catalogueService;

	@RequestMapping(method = RequestMethod.GET)
	public List<ModeletelephoneDTO> getAllModeles() {
		return catalogueService.findModeletelephones();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModeletelephoneDTO getModeleById(@PathVariable("id") Long id) {
		return catalogueService.findModele(id).get();
	}

	@RequestMapping(value = "taille/{taille}", method = RequestMethod.GET)
	public List<ModeletelephoneDTO> getModelesByTaillecran(@PathVariable("taille") Double taille) {
		return catalogueService.findModeletelephonesByTailleEcran(taille);
	}

	@RequestMapping(value = "capacite/{capacite}", method = RequestMethod.GET)
	public List<ModeletelephoneDTO> getModelesByCapaciteStockage(@PathVariable("capacite") int capacite) {
		return catalogueService.findModeletelephonesByCapaciteStockage(capacite);
	}

	@RequestMapping(value = "/addmodele", method = RequestMethod.POST)
	public ResponseEntity<MessageDTO> createModele(
			@Valid @RequestBody ModeletelephoneCreationDTO modeletelephoneCreationDTO) {

		try {
			catalogueService.createModele(modeletelephoneCreationDTO);
			return ResponseEntity.ok(new MessageDTO("Création nouveau modèle réussie"));

		} catch (ReferenceModeleExistanteException e) {
			return ResponseEntity.ok(new MessageDTO("La réfèrence de ce télèphone existe déja dans l'inventaire"));

		} catch (ConstraintViolationException e) {
			return ResponseEntity
					.ok(new MessageDTO("Les données entrées pour le télèphone sont erronées \n" + e.getMessage()));

		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(value = "/updatemodele", method = RequestMethod.PUT)
	public ResponseEntity<MessageDTO> updateModele(@Valid @RequestBody ModeletelephoneUpdatedDTO modeletelephoneUpdatedDTO) {
		
		try {
			catalogueService.updateModele(modeletelephoneUpdatedDTO);
			return ResponseEntity.ok(new MessageDTO("Modification du modèle réussie"));

		} catch (ReferenceModeleExistanteException e) {
			return ResponseEntity.ok(new MessageDTO("La réfèrence de ce télèphone existe déja dans l'inventaire"));

		} catch (ConstraintViolationException e) {
			return ResponseEntity
					.ok(new MessageDTO("Les données entrées pour le télèphone sont erronées \n" + e.getMessage()));

		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/*
	 * En cas d'erreur sur le formulaire ModeletelephoneUpdatedDTO les erreurs seront renvoyés dans un messageDTO
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MessageDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		
		StringBuilder str=new StringBuilder();
		for(String field : errors.keySet())
			str.append(field+" : "+errors.get(field)+"\n");
		

		MessageDTO messageDTO=new MessageDTO(str.toString());
		return ResponseEntity.ok(messageDTO);
	}

}
