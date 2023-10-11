package com.phonestore;

import com.phonestore.administration.dto.UserCreationDTO;
import com.phonestore.catalogue.domain.Modeletelephone;
import com.phonestore.catalogue.dto.AssociationmodelereparationDTO;
import com.phonestore.catalogue.dto.MarqueDTO;
import com.phonestore.catalogue.dto.ModeletelephoneCreationDTO;
import com.phonestore.catalogue.dto.ModeletelephoneUpdatedDTO;
import com.phonestore.prestation.dto.PrestationCreationDTO;

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
	
	public static AssociationmodelereparationDTO defaultAssociationmodelereparationDTO() {
		
		return new AssociationmodelereparationDTO(0L, 2L, 3L, "remplacement caméra avant", "iphone_13_pro", "APPLE", 229, 1);
	}
	
	public static AssociationmodelereparationDTO defaultAssociationUpdatedmodelereparationDTO() {
		
		return new AssociationmodelereparationDTO(1L, 1L, 2L, "remplacement batterie", "iphone_14", "APPLE", 130, 1);
	}
	
	
	public static PrestationCreationDTO defaultPrestationCreationDTO() {
		
		return new PrestationCreationDTO("client@hotmail.fr", 1L, 2L, "IPHONEXX14", 328, "CREE");
	}
	
	public static PrestationCreationDTO defaultPrestationCreationDTOUserFaux() {
		
		return new PrestationCreationDTO("nobody@hotmail.fr", 1L, 2L, "IPHONEXX14", 328, "CREE");
	}
	
public static PrestationCreationDTO defaultPrestationCreationDTOUserNonUsager() {
		
		return new PrestationCreationDTO("gerant@hotmail.fr", 1L, 2L, "IPHONEXX14", 328, "CREE");
	}
	
	

	public static PrestationCreationDTO defaultPrestationCreationDTOModeleFaux() {
		
		return new PrestationCreationDTO("client@hotmail.fr", 3000L, 2L, "IPHONEXX14", 328, "CREE");
	}
	
public static PrestationCreationDTO defaultPrestationCreationDTOAssociationFausse() {
		
		return new PrestationCreationDTO("client@hotmail.fr", 1L, 3000L, "IPHONEXX14", 328, "CREE");
	}
	
	
	
public static UserCreationDTO userCreationDTOEmploye() {
	
	return new UserCreationDTO("employe@hotmail.fr", "John", "Lebosseur", "0611121314", "employe01", "employe01");
}

public static UserCreationDTO userCreationDTOUsager() {
	
	return new UserCreationDTO("client@hotmail.fr", "Jack", "Leclient", "0621222324", "client01", "client01");
}
	

}
