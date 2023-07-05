package com.phonestore;

import com.phonestore.catalogue.domain.Modeletelephone;
import com.phonestore.catalogue.dto.ModeletelephoneCreationDTO;
import com.phonestore.catalogue.dto.ModeletelephoneUpdatedDTO;

public class DefaultContent {

	public static Modeletelephone defaultModele() {

		Modeletelephone newModele = new Modeletelephone();
		newModele.setId(0L);
		newModele.setTailleEcran(6.1);
		newModele.setCapaciteStockage(128);
		newModele.setImagePath("iphone_12.jpg");
		newModele.setPrix(809);
		newModele.setReference("iphone_12");

		return newModele;

	}

	public static ModeletelephoneCreationDTO defaultModeleCreationDTO() {

		return new ModeletelephoneCreationDTO("iphone_12", 809, 6.1, 128, 1L);

	}
	
	public static ModeletelephoneUpdatedDTO defaultModeleUpdatedDTO() {

		return new ModeletelephoneUpdatedDTO(1L, "iphone_SE", 559, 4.7, 64, 1L);

	}
	
	
	
	

}
