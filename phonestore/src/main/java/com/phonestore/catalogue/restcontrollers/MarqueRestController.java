package com.phonestore.catalogue.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phonestore.catalogue.dao.MarqueDAO;
import com.phonestore.catalogue.domain.Marque;
import com.phonestore.catalogue.dto.MarqueDTO;
import com.phonestore.catalogue.dto.ModeletelephoneDTO;
import com.phonestore.catalogue.service.CatalogueService;

@RestController
@RequestMapping("/api/marques")
@CrossOrigin
public class MarqueRestController {
	
	   @Autowired
	    CatalogueService catalogueService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<MarqueDTO> getAllMarques() {
		return catalogueService.findMarques();
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public List<ModeletelephoneDTO> getModelesByIdMarque(@PathVariable("id") Long id) {
		return catalogueService.findModeletelephonesByMarque(id);
	}
	
	
	
	

}
