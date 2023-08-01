package com.phonestore.prestation.dto;

import java.util.Date;

import com.phonestore.administration.domain.User;
import com.phonestore.catalogue.domain.Associationmodelereparation;
import com.phonestore.prestation.domain.Prestation;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestationDTO {
	
	@Min(1)
	private Long id;
	
	@NotBlank
	private String identifiantUsager;
	
	@Min(1)
	private Long idAssociation;
	
	@NotBlank
	private String nomModele;
	
	@NotBlank
	private String nomMarque;
	
	@NotBlank
	private String nomReparation;
	
	@NotBlank
	private String numeroSerie;
	
	@Min(1)
	private double prix;
	
	@NotBlank
	private String status;
	
	@NotNull
	private Date dateCreation;

}
