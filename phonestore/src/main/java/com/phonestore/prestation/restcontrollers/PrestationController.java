package com.phonestore.prestation.restcontrollers;

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
import com.phonestore.catalogue.dto.ReparationDTO;
import com.phonestore.catalogue.exception.AssociationmodeletelephonereparationNonExistanteException;
import com.phonestore.catalogue.exception.IdModeleNonExistantException;
import com.phonestore.catalogue.exception.ReferenceModeleExistanteException;
import com.phonestore.prestation.dto.PrestationCreationDTO;
import com.phonestore.prestation.dto.PrestationDTO;
import com.phonestore.prestation.exception.PrestationNonExistanteException;
import com.phonestore.prestation.exception.UserNonExistantException;
import com.phonestore.prestation.service.PrestationService;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/prestations")
@CrossOrigin
public class PrestationController {

	@Autowired
	PrestationService prestationService;

	/*
	 * Methode qui permet de renvoyer toutes les prestations d'un usager
	 */
	@RequestMapping(value = "/list/{username}", method = RequestMethod.GET)
	public List<PrestationDTO> getPrestationsUsager(@PathVariable("username") String username) {
		return prestationService.findPrestationByIdentifiantUsager(username);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<PrestationDTO> getAllPrestations() {
		return prestationService.findAll();
	}

	/*
	 * Methode qui permet de renvoyer toutes les prestations d'un appareil par son
	 * numero de serie
	 */

	@RequestMapping(value = "/all/numeroserie/{numeroSerie}", method = RequestMethod.GET)
	public List<PrestationDTO> getPrestationssByNumeroSerie(@PathVariable("numeroSerie") String numeroSerie) {
		return prestationService.findPrestationByNumeroSerie(numeroSerie);
	}

	/*
	 * Methode qui permet de renvoyer une prestation dans une liste avec un element
	 * unique cela car dans le front-end on utilise une list et elle sera mise a
	 * jour selon le critere de recherche
	 */

	@RequestMapping(value = "/all/id/{id}", method = RequestMethod.GET)
	public List<PrestationDTO> getPrestationssByIdList(@PathVariable("id") Long id) {
		return prestationService.findPrestationByIdList(id);
	}

	@RequestMapping(value = "one/{id}", method = RequestMethod.GET)
	public PrestationDTO getOnePrestationsUsager(@PathVariable("id") Long id) {
		return prestationService.findPrestationById(id).get();
	}

	@RequestMapping(value = "/addprestation", method = RequestMethod.POST)
	public ResponseEntity<MessageDTO> createPrestation(
			@Valid @RequestBody PrestationCreationDTO prestationCreationDTO) {

		try {
			prestationService.createPrestation(prestationCreationDTO);
			return ResponseEntity.ok(new MessageDTO("Création de la prestation réussie"));

		} catch (IdModeleNonExistantException e) {
			return ResponseEntity.ok(new MessageDTO("Le télèphone n'existe pas  dans l'inventaire"));

		} catch (AssociationmodeletelephonereparationNonExistanteException e) {
			return ResponseEntity.ok(new MessageDTO("La réparation  n'existe pas pour ce télèphone"));

		} catch (UserNonExistantException e) {
			return ResponseEntity.ok(new MessageDTO("L'usager n'a pas pu être identifié"));

		} catch (ConstraintViolationException e) {
			return ResponseEntity
					.ok(new MessageDTO("Les données entrées pour la prestation sont erronées \n" + e.getMessage()));

		} catch (Exception e) {
			return ResponseEntity.ok(new MessageDTO("Un problème technique est survenu : \n" + e.getMessage()));
		}
	}

	/*
	 * Methode qui permet de modifier le prix ou le statut de la prestation
	 * uniquement en theorie il ne devrait donc pas y avoir d'exception sauf
	 * exception sur le prix
	 */
	@RequestMapping(value = "/updateprestation", method = RequestMethod.PUT)
	public ResponseEntity<MessageDTO> updatePrestation(@Valid @RequestBody PrestationDTO prestationDTO) {

		try {

			prestationService.updatePrestation(prestationDTO);

			return ResponseEntity.ok(new MessageDTO("Modification de la prestation réussie"));

		} catch (PrestationNonExistanteException e) {
			return ResponseEntity.ok(new MessageDTO("La prestation n'a pas pu etre identifiée"));

		} catch (AssociationmodeletelephonereparationNonExistanteException e) {
			return ResponseEntity.ok(new MessageDTO("La réparation  n'existe pas pour ce télèphone"));

		} catch (UserNonExistantException e) {
			return ResponseEntity.ok(new MessageDTO("L'usager n'a pas pu être identifié"));

		} catch (ConstraintViolationException e) {
			return ResponseEntity
					.ok(new MessageDTO("Les données entrées pour la prestation sont erronées \n" + e.getMessage()));

		} catch (Exception e) {
			return ResponseEntity.ok(new MessageDTO("Un problème technique est survenu : \n" + e.getMessage()));
		}
	}

	/*
	 * En cas d'erreur sur le formulaire PrestationCreationDTO les erreurs seront
	 * renvoyés dans un messageDTO
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
		String message = mess.substring(0, mess.length() - 2);

		MessageDTO messageDTO = new MessageDTO(message);
		System.out.println(messageDTO.getMessage());
		return ResponseEntity.ok(messageDTO);
	}
}
