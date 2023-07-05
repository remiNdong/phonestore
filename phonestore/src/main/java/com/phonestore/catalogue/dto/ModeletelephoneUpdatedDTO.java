package com.phonestore.catalogue.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
	private double TailleEcran;
	
	@Min(1)
	private int capaciteStockage;
	
	@Min(1)
	private Long idMarque;

}
