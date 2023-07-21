package com.phonestore.catalogue.service;


import org.springframework.beans.factory.annotation.Autowired;

import com.phonestore.catalogue.dao.MarqueDAO;
import com.phonestore.catalogue.domain.Marque;
import com.phonestore.catalogue.domain.Modeletelephone;
import com.phonestore.catalogue.dto.MarqueDTO;
import com.phonestore.catalogue.dto.ModeletelephoneCreationDTO;
import com.phonestore.catalogue.dto.ModeletelephoneDTO;

public class CatalogueDTOMapper {
	
	@Autowired
	MarqueDAO marqueDAO;

	public static MarqueDTO marqueToDTO(Marque marque) {

		return new MarqueDTO(marque.getId(), marque.getNom());

	}

public static ModeletelephoneDTO modeletelephoneToDTO(Modeletelephone modeletelephone) {
		
	MarqueDTO marqueDTO=new MarqueDTO(modeletelephone.getMarque().getId(), modeletelephone.getMarque().getNom());
		return new ModeletelephoneDTO(modeletelephone.getId(), modeletelephone.getReference(), modeletelephone.getPrix(), modeletelephone.getTailleEcran(), modeletelephone.getCapaciteStockage(),
				modeletelephone.getImagePath(),marqueDTO);
		
	}


public static Modeletelephone fromDTOtoModeletelephone(ModeletelephoneCreationDTO modeletelephoneCreationDTO) {
	
	
	return new Modeletelephone(0L, modeletelephoneCreationDTO.getReference().toLowerCase(), modeletelephoneCreationDTO.getPrix(), modeletelephoneCreationDTO.getTailleEcran(), 
			modeletelephoneCreationDTO.getCapaciteStockage(), modeletelephoneCreationDTO.getReference().toLowerCase()+".jpg", null);
}






}
