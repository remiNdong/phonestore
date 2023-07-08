package com.phonestore.catalogue.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModeletelephoneUpdatedDTO {
	
	@Min(1)
	private Long id;
	
	@NotBlank
	private String reference;
	
	@Min(1)
	private double prix;
	
	@Min(1)
	private double tailleEcran;
	
	@Min(1)
	private int capaciteStockage;
	
	@NotNull
	private MarqueDTO marqueDTO;

}
