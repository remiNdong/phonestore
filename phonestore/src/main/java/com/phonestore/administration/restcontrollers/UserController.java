package com.phonestore.administration.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phonestore.administration.dto.UserDTO;
import com.phonestore.administration.service.UserService;

@RestController
@RequestMapping("/api/usagers")
@CrossOrigin
public class UserController {
	
	  @Autowired
	    UserService userService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<UserDTO> getAllUsagers() {
		return userService.findAll();
	}
	
	@RequestMapping(value = "/one/{username}", method = RequestMethod.GET)
	public UserDTO getOneUsager(@PathVariable("username") String username) {
		return userService.findUserDTOByUsername(username);
	}
	
	@RequestMapping(value = "/oneself/{username}", method = RequestMethod.GET)
	public UserDTO getOneSelfUsager(@PathVariable("username") String username) {
		return userService.findUserDTOByUsername(username);
		
		
	}
	
	@RequestMapping(value = "/all/{nom}", method = RequestMethod.GET)
	public List<UserDTO> getAllUsagersByNom(@PathVariable("nom") String nom) {
		return userService.findUserDTOByNom(nom);
	}
	
	

}
