package com.phonestore.catalogue.service;

import com.phonestore.catalogue.domain.Marque;
import com.phonestore.catalogue.domain.Modeletelephone;
import com.phonestore.catalogue.dto.MarqueDTO;
import com.phonestore.catalogue.dto.ModeletelephoneDTO;

public class CatalogueDTOMapper {

	public static MarqueDTO marqueToDTO(Marque marque) {

		return new MarqueDTO(marque.getId(), marque.getNom());

	}

public static ModeletelephoneDTO modeletelephoneToDTO(Modeletelephone modeletelephone) {
		
		return new ModeletelephoneDTO(modeletelephone.getId(), modeletelephone.getReference(), modeletelephone.getPrix(), modeletelephone.getTailleEcran(), modeletelephone.getCapaciteStockage(),
				modeletelephone.getImagePath(), modeletelephone.getMarque().getId(), modeletelephone.getMarque().getNom());
		
	}

}
