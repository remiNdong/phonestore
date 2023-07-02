package com.phonestore.catalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModeletelephoneDTO {

	private Long id;
	private String reference;
	private double prix;
	private double TailleEcran;
	private int capaciteStockage;
	private String imagePath;
	private Long idMarque;
	private String nomMarque;
	
	
}
