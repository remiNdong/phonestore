package com.phonestore.administration.restcontrollers;

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

import com.phonestore.administration.domain.User;
import com.phonestore.administration.dto.UserCreationDTO;
import com.phonestore.administration.exception.PasswordDoesntMatchException;
import com.phonestore.administration.exception.UserDejaExistantException;
import com.phonestore.administration.service.UserService;
import com.phonestore.catalogue.dto.MessageDTO;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

/*
 * tests sur les restrictions de l'url dans POSTMAN
 * tests non intégrés à Junit car les token sont limités dans le temps
 * ne sera pas utilisée dans les uses cases
 */

@RestController
@CrossOrigin//(origins = "*")
public class UserRestController {
	
	@Autowired
	UserService userService;

	@RequestMapping(path = "all", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userService.findAllUsers();
	}
	
	
	@RequestMapping(value = "one/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable("id") Long id) {
		return userService.findById(id);
	}
	
/*
 * Methode crée pour efectuer des tests avec Postman
 */
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String getSimpleUser() {
		return "Autorisé à un User connecté";
	}
	
	
	/*
	 * Methode crée pour efectuer des tests avec Postman
	 */
	@RequestMapping(value = "everybody", method = RequestMethod.GET)
	public String getEverybody() {
		return "Autorisé à un tout le monde";
	}
	
	
	
	@RequestMapping(value = "/addemploye", method = RequestMethod.POST)
	public ResponseEntity<MessageDTO> addEmploye(
			@Valid @RequestBody UserCreationDTO userCreationDTO) {

		try {
			userService.saveEmploye(userCreationDTO);
			return ResponseEntity.ok(new MessageDTO("Création nouvel employé réussie"));

		} catch (UserDejaExistantException e) {
			return ResponseEntity.ok(new MessageDTO("Un utilisateur est déja identifié par cet email"));
			
		} catch (PasswordDoesntMatchException e) {
			return ResponseEntity.ok(new MessageDTO("Les deux mots de passe doivent être identiques"));

		} catch (ConstraintViolationException e) {
			return ResponseEntity
					.ok(new MessageDTO("Les données entrées pour l'employé sont erronées \n" + e.getMessage()));

		} catch (Exception e) {
			return ResponseEntity
					.ok(new MessageDTO("Un problème technique est survenu : \n" + e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/addusager", method = RequestMethod.POST)
	public ResponseEntity<MessageDTO> addClient(
			@Valid @RequestBody UserCreationDTO userCreationDTO) {

		try {
			userService.saveUsager(userCreationDTO);
			return ResponseEntity.ok(new MessageDTO("Création nouvel usager réussie"));

		} catch (UserDejaExistantException e) {
			return ResponseEntity.ok(new MessageDTO("Un utilisateur est déja identifié par cet email"));
			
		} catch (PasswordDoesntMatchException e) {
			return ResponseEntity.ok(new MessageDTO("Les deux mots de passe doivent être identiques"));

		} catch (ConstraintViolationException e) {
			return ResponseEntity
					.ok(new MessageDTO("Les données entrées pour l'usager sont erronées \n" + e.getMessage()));

		} catch (Exception e) {
			return ResponseEntity
					.ok(new MessageDTO("Un problème technique est survenu : \n" + e.getMessage()));
		}
	}
	
	
	
	/*
	 * En cas d'erreur sur le formulaire userCreationDTO les erreurs
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
