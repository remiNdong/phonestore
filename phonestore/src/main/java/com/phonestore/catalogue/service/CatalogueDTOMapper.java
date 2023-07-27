package com.phonestore.catalogue.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.phonestore.catalogue.dao.MarqueDAO;
import com.phonestore.catalogue.domain.Associationmodelereparation;
import com.phonestore.catalogue.domain.Marque;
import com.phonestore.catalogue.domain.Modeletelephone;
import com.phonestore.catalogue.domain.Reparation;
import com.phonestore.catalogue.dto.AssociationmodelereparationDTO;
import com.phonestore.catalogue.dto.MarqueDTO;
import com.phonestore.catalogue.dto.ModeletelephoneCreationDTO;
import com.phonestore.catalogue.dto.ModeletelephoneDTO;
import com.phonestore.catalogue.dto.ReparationDTO;

public class CatalogueDTOMapper {
	
	@Autowired
	MarqueDAO marqueDAO;

	public static MarqueDTO marqueToDTO(Marque marque) {

		return new MarqueDTO(marque.getId(), marque.getNom());

	}
	
	public static ReparationDTO reparationToDTO(Reparation reparation) {

		return new ReparationDTO(reparation.getId(), reparation.getNom());

	}
	
	public static AssociationmodelereparationDTO associationmodelereparationToDTO(Associationmodelereparation associationmodelereparation) {
		

		return new AssociationmodelereparationDTO(associationmodelereparation.getId(), associationmodelereparation.getModeletelephone().getId(), associationmodelereparation.getReparation().getId(),associationmodelereparation.getReparation().getNom(),associationmodelereparation.getModeletelephone().getReference(),associationmodelereparation.getModeletelephone().getMarque().getNom(), associationmodelereparation.getPrix());

	}
	
	


public static ModeletelephoneDTO modeletelephoneToDTO(Modeletelephone modeletelephone) {
		
	MarqueDTO marqueDTO=new MarqueDTO(modeletelephone.getMarque().getId(), modeletelephone.getMarque().getNom());
	
	List<AssociationmodelereparationDTO> associationsDTO=new ArrayList<AssociationmodelereparationDTO>();
	
	for(Associationmodelereparation association : modeletelephone.getAssociations())
		associationsDTO.add(associationmodelereparationToDTO(association));
	
	
		return new ModeletelephoneDTO(modeletelephone.getId(), modeletelephone.getReference(), modeletelephone.getPrix(), modeletelephone.getTailleEcran(), modeletelephone.getCapaciteStockage(),
				modeletelephone.getImagePath(),marqueDTO,associationsDTO);
		
	}


public static Modeletelephone fromDTOtoModeletelephone(ModeletelephoneCreationDTO modeletelephoneCreationDTO) {
	
	
	return new Modeletelephone(0L, modeletelephoneCreationDTO.getReference().toLowerCase(), modeletelephoneCreationDTO.getPrix(), modeletelephoneCreationDTO.getTailleEcran(), 
			modeletelephoneCreationDTO.getCapaciteStockage(), modeletelephoneCreationDTO.getReference().toLowerCase()+".jpg", null,null);
}






}
