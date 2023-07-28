package com.phonestore.catalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociationmodelereparationDTO {
	
	private Long id;
	private Long idModeletelephone;
	private Long idReparation;
	private String nomReparation;
	private String nomModele;
	private String marqueModele;
	private double prix;
	private int praticable;

}
