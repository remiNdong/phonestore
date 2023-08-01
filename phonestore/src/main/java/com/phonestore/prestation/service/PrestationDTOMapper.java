package com.phonestore.prestation.service;

import com.phonestore.prestation.domain.Prestation;
import com.phonestore.prestation.dto.PrestationDTO;

public class PrestationDTOMapper {
	
	public static  PrestationDTO prestationToDTO(Prestation prestation) {
		
		
		PrestationDTO prestationDTO=new PrestationDTO(prestation.getId(), prestation.getUsager().getUsername(), prestation.getAssociation().getId(),
				prestation.getAssociation().getModeletelephone().getReference(),prestation.getAssociation().getModeletelephone().getMarque().getNom(),
				prestation.getAssociation().getReparation().getNom(), prestation.getNumeroSerie(), prestation.getPrix(),prestation.getStatus(), prestation.getDateCreation());
		
		return prestationDTO;
	}

}
