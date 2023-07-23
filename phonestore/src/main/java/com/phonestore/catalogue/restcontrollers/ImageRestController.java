package com.phonestore.catalogue.restcontrollers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.phonestore.catalogue.dto.ModeletelephoneDTO;
import com.phonestore.catalogue.exception.IdModeleNonExistantException;
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
	
	@RequestMapping(value = "/uploadImage/{reference}", method = RequestMethod.POST)
	public void uploadImage(@RequestParam("image") MultipartFile file, @PathVariable("reference") String  reference)
			throws IOException {
		List <ModeletelephoneDTO> list = catalogueService.findModeletelephoneByReference(reference.toLowerCase());
		
		if(list.size()!=1) 
			throw new IdModeleNonExistantException();
			
		
		
		//en theorie a l'utilisation de cette méthode par l'appel d'angular, le modele vient d'etre crée
		//son ImagePath est deja a jour dès la creation
		
		ModeletelephoneDTO modeletelephoneDTO=list.get(0);
		
		
		Files.write(Paths.get(System.getProperty("user.home") + "/images/" + modeletelephoneDTO.getImagePath()), file.getBytes());

	}
	
	@RequestMapping(value = "/delete/{imagePath}", method = RequestMethod.DELETE)
	public void deleteImage(@PathVariable("imagePath") String  imagePath) throws IOException {
		
		Files.delete(Paths.get(System.getProperty("user.home") + "/images/"+imagePath));
	}


}
