package com.phonestore.catalogue.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Modeletelephone {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@NotNull
	private Long id;
	
	@Column(unique = true)
	@NotNull
	private String reference;
	
	@Column
	@NotNull
	private double prix;
	
	@Column
	@NotNull
	private double tailleEcran;
	
	@Column
	@NotNull
	private int capaciteStockage;
	
	@Column
	@NotNull
	private String imagePath;
	
	@ManyToOne
	Marque marque;

	

}
