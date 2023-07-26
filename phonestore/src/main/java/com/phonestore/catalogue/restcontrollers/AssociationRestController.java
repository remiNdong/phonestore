package com.phonestore.catalogue.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phonestore.catalogue.dto.AssociationmodelereparationDTO;
import com.phonestore.catalogue.service.CatalogueService;

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

}
