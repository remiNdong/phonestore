package com.phonestore.catalogue.restcontrollers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phonestore.catalogue.dto.ModeletelephoneDTO;
import com.phonestore.catalogue.service.CatalogueService;


@RestController
@RequestMapping("/api/image")
@CrossOrigin
public class ImageRestController {
	
	
	@Autowired
	CatalogueService catalogueService;
	
	@RequestMapping(value = "/loadImage/{id}", method = RequestMethod.GET, produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImage(@PathVariable("id") Long id) throws IOException {

		ModeletelephoneDTO modeletelephoneDTO = catalogueService.findModele(id).get();
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/images/" + modeletelephoneDTO.getImagePath()));
	}

}
