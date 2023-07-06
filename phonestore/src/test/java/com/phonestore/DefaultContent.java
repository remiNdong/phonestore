package com.phonestore;

import com.phonestore.catalogue.domain.Modeletelephone;
import com.phonestore.catalogue.dto.MarqueDTO;
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
	
	public static MarqueDTO defaultMarqueDTO() {
		return new MarqueDTO(1L,"APPLE");
	}
	
	public static MarqueDTO defaultMarqueDTOFalse() {
		return new MarqueDTO(6L,"TELBIDON");
	}

	public static ModeletelephoneCreationDTO defaultModeleCreationDTO() {

		return new ModeletelephoneCreationDTO(0L,"iphone_12", 809, 6.1, 128, defaultMarqueDTO());

	}
	
	public static ModeletelephoneUpdatedDTO defaultModeleUpdatedDTO() {

		return new ModeletelephoneUpdatedDTO(1L, "iphone_SE", 559, 4.7, 64, defaultMarqueDTO());

	}
	
	
	
	

}
