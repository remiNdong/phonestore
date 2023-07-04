package com.phonestore.catalogue.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phonestore.catalogue.dto.ModeletelephoneDTO;
import com.phonestore.catalogue.service.CatalogueService;

@RestController
@RequestMapping("/api/modeles")
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
	

}
