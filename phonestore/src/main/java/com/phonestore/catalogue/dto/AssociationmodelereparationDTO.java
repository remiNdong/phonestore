package com.phonestore.catalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociationmodelereparationDTO {
	
	private Long id;
	private Long idModeleTelephone;
	private Long idReparation;
	private String nomReparation;
	private double prix;

}
