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
import com.phonestore.storage.StorageService;


@RestController
@RequestMapping("/api/image")
@CrossOrigin
public class ImageRestController {
	
	
	@Autowired
	CatalogueService catalogueService;
	
	@Autowired
	StorageService storageService;
	
	/*
	 * Methode qui permettra de charger l'image dans un tableau de byte
	 * depuis l'id du modele et ensuite grace au path de l'image enregistrée dans un dossier
	 * en local dans C:\Users\boma\images
	 * Pas utilisée dans le front end final car repository public pour les images
	 */
	@RequestMapping(value = "/loadImage/{id}", method = RequestMethod.GET, produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImage(@PathVariable("id") Long id) throws IOException {

		ModeletelephoneDTO modeletelephoneDTO = catalogueService.findModele(id).get();
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/images/" + modeletelephoneDTO.getImagePath()));
	}
	
	
	
	/*
	 * Methode qui permet d'enregistrer l'image du modele en local dans le dossier C:\Users\boma\images
	 * Methode modifiee pour enregistrer les images sur AWS
	 */
	@RequestMapping(value = "/uploadImage/{reference}", method = RequestMethod.POST)
	public void uploadImage(@RequestParam("image") MultipartFile file, @PathVariable("reference") String  reference)
			throws IOException {
		List <ModeletelephoneDTO> list = catalogueService.findModeletelephoneByReference(reference.toLowerCase());
		
		if(list.size()!=1) 
			throw new IdModeleNonExistantException();
			
		
		
		//en theorie a l'utilisation de cette méthode par l'appel d'angular, le modele vient d'etre crée
		//son ImagePath est deja a jour dès la creation
		
		ModeletelephoneDTO modeletelephoneDTO=list.get(0);
		
		
		//Files.write(Paths.get(System.getProperty("user.home") + "/images/" + modeletelephoneDTO.getImagePath()), file.getBytes());
		
		storageService.store(file, reference);

	}
	
	/*
	 * Methode qui permet de supprimer l'image du modele du dossier C:\Users\boma\images
	 * sera utilisée en cas de remplacement par une nouvelle image -> update du modele
	 */
	
	@RequestMapping(value = "/delete/{imagePath}", method = RequestMethod.DELETE)
	public void deleteImage(@PathVariable("imagePath") String  imagePath) throws IOException {
		
		//Files.delete(Paths.get(System.getProperty("user.home") + "/images/"+imagePath));
		storageService.delete(imagePath);
	}


}
