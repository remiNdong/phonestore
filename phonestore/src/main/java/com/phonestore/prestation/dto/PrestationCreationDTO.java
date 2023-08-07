package com.phonestore.prestation.dto;

import java.util.Date;

import com.phonestore.catalogue.dto.MarqueDTO;
import com.phonestore.catalogue.dto.ModeletelephoneCreationDTO;
import com.phonestore.catalogue.dto.ModeletelephoneDTO;
import com.phonestore.catalogue.dto.ReparationDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestationCreationDTO {
	


	@NotBlank
	private String identifiantUsager;
	
	@Min(1)
	private Long idModele;
	

	@Min(1)
	private Long idAssociation;
	
	@NotBlank
	private String numeroSerie;
	
	@Min(1)
	private double prix;
	
	@NotBlank
	private String status;
	
	
	
	
	
	
	

}
