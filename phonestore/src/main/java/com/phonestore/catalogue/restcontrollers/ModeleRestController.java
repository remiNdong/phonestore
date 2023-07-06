package com.phonestore.catalogue.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phonestore.catalogue.dto.ModeletelephoneCreationDTO;
import com.phonestore.catalogue.dto.ModeletelephoneDTO;
import com.phonestore.catalogue.dto.ModeletelephoneUpdatedDTO;
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

	@RequestMapping(value = "taille/{taille}", method = RequestMethod.GET)
	public List<ModeletelephoneDTO> getModelesByTaillecran(@PathVariable("taille") Double taille) {
		return catalogueService.findModeletelephonesByTailleEcran(taille);
	}

	@RequestMapping(value = "capacite/{capacite}", method = RequestMethod.GET)
	public List<ModeletelephoneDTO> getModelesByCapaciteStockage(@PathVariable("capacite") int capacite) {
		return catalogueService.findModeletelephonesByCapaciteStockage(capacite);
	}

	@RequestMapping(value = "/addmodele", method = RequestMethod.POST)
	public Long createModele(@RequestBody ModeletelephoneCreationDTO modeletelephoneCreationDTO) {
		return catalogueService.createModele(modeletelephoneCreationDTO);
	}

	@RequestMapping(value = "/updatemodele", method = RequestMethod.POST)
	public Long updateModele(@RequestBody ModeletelephoneUpdatedDTO modeletelephoneUpdatedDTO) {
		return catalogueService.updateModele(modeletelephoneUpdatedDTO);
	}

}
