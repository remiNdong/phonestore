package com.phonestore;


import com.phonestore.catalogue.domain.Modeletelephone;


public class DefaultContent {



	public static Modeletelephone defaultModele() {
		
		Modeletelephone newModele=new Modeletelephone();
		newModele.setId(0L);
		newModele.setTailleEcran(6.1);
		newModele.setCapaciteStockage(128);
		newModele.setImagePath("iphone_12.jpg");
		newModele.setPrix(809);
		newModele.setReference("iphone_12");
		
		return newModele;

	}

}
