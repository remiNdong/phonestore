package com.phonestore.catalogue.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phonestore.catalogue.dto.MarqueDTO;
import com.phonestore.catalogue.dto.ModeletelephoneDTO;
import com.phonestore.catalogue.dto.ReparationDTO;
import com.phonestore.catalogue.service.CatalogueService;

@RestController
@RequestMapping("/api/reparations")
@CrossOrigin
public class ReparationController {
	
	   @Autowired
	    CatalogueService catalogueService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<ReparationDTO> getAllReparations() {
		return catalogueService.findReparations();
	}

	/*
	 * Methode qui permet de trouver les type de reparations pas encore 
	 * pratiquees sur un modele
	 */
	@RequestMapping(value = "/{idModele}", method = RequestMethod.GET)
	public List<ReparationDTO> getRepparationsNonPratiquees(@PathVariable("idModele") Long idModele) {
		return catalogueService.findReparationsNonPratiquees(idModele);
	}
}
