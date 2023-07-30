package com.phonestore.catalogue.restcontrollers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.phonestore.catalogue.dto.AssociationmodelereparationDTO;
import com.phonestore.catalogue.dto.MessageDTO;
import com.phonestore.catalogue.dto.ModeletelephoneCreationDTO;
import com.phonestore.catalogue.dto.ModeletelephoneDTO;
import com.phonestore.catalogue.dto.ModeletelephoneUpdatedDTO;
import com.phonestore.catalogue.exception.AssociationmodeletelephonereparationDejaExistanteException;
import com.phonestore.catalogue.exception.ReferenceModeleExistanteException;
import com.phonestore.catalogue.service.CatalogueService;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/associations")
@CrossOrigin
public class AssociationRestController {
	
	 @Autowired
	    CatalogueService catalogueService;
	 
	 @RequestMapping(value = "/liste/{id}", method = RequestMethod.GET)
		public List<AssociationmodelereparationDTO> getAssociationsByModele(@PathVariable("id") Long id) {
			return catalogueService.findAssociationmodelereparationByModele(id);
		}
	 
	 @RequestMapping(value = "/one/{id}", method = RequestMethod.GET)
		public AssociationmodelereparationDTO getAssociationById(@PathVariable("id") Long id) {
			return catalogueService.findAssociationById(id).get();
		}
	 

		@RequestMapping(value = "/addassociation", method = RequestMethod.POST)
		public ResponseEntity<MessageDTO> createAssociation(
				@Valid @RequestBody AssociationmodelereparationDTO associationmodelereparationDTO) {

			try {
				catalogueService.createAssociation(associationmodelereparationDTO);
				return ResponseEntity.ok(new MessageDTO("Création nouvelle association réussie"));

			} catch (AssociationmodeletelephonereparationDejaExistanteException e) {
				return ResponseEntity.ok(new MessageDTO("La réparation de ce type existe déja pour ce  télèphone"));

			} catch (ConstraintViolationException e) {
				return ResponseEntity
						.ok(new MessageDTO("Les données entrées pour la réparation sont erronées \n" + e.getMessage()));

			} catch (Exception e) {
				return ResponseEntity
						.ok(new MessageDTO("Un problème technique est survenu : \n" + e.getMessage()));
			}
		}
		
		@RequestMapping(value = "/updateassociation", method = RequestMethod.PUT)
		public ResponseEntity<MessageDTO> updateModele(
				@Valid @RequestBody AssociationmodelereparationDTO associationmodelereparationDTO) {

			try {

				catalogueService.updateAssociation(associationmodelereparationDTO);

				return ResponseEntity.ok(new MessageDTO("Modification de la réparation réussie"));

		

			} catch (ConstraintViolationException e) {
				return ResponseEntity
						.ok(new MessageDTO("Les données entrées pour la réparation sont erronées \n" + e.getMessage()));

			} catch (Exception e) {
				return ResponseEntity
						.ok(new MessageDTO("Un problème technique est survenu : \n" + e.getMessage()));
			}
		}
		
		
		/*
		 * En cas d'erreur sur le formulaire AssociaonmodelereparationDTO les erreurs
		 * seront renvoyés dans un messageDTO
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

			StringBuilder str = new StringBuilder();
			for (String field : errors.keySet())
				str.append(field + " : " + errors.get(field) + "\n" + " - ");

			String mess = str.toString();
			String message = mess.substring(0, mess.length()-2);

			MessageDTO messageDTO = new MessageDTO(message);
			System.out.println(messageDTO.getMessage());
			return ResponseEntity.ok(messageDTO);
		}

}
