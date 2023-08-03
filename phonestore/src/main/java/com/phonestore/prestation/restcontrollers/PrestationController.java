package com.phonestore.prestation.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phonestore.catalogue.dto.ReparationDTO;
import com.phonestore.prestation.dto.PrestationDTO;
import com.phonestore.prestation.service.PrestationService;

@RestController
@RequestMapping("/api/prestations")
@CrossOrigin
public class PrestationController {
	
	@Autowired
	PrestationService prestationService;
	
	@RequestMapping(value = "/list/{username}", method = RequestMethod.GET)
	public List<PrestationDTO> getPrestationsUsager(@PathVariable("username") String username) {
		return prestationService.findPrestationByIdentifiantUsager(username);
	}
	
	
	@RequestMapping(value = "one/{id}", method = RequestMethod.GET)
	public PrestationDTO getOnePrestationsUsager(@PathVariable("id") Long id) {
		return prestationService.findPrestationById(id).get();
	}

}
