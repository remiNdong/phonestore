package com.phonestore.catalogue.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociationmodelereparationDTO {
	
	
	private Long id;
	
	@Min(1)
	private Long idModeletelephone;
	
	@Min(1)
	private Long idReparation;
	
	@NotBlank
	private String nomReparation;
	
	@NotBlank
	private String nomModele;
	
	@NotBlank
	private String marqueModele;
	
	@Min(1)
	private double prix;
	
	private int praticable;

}
