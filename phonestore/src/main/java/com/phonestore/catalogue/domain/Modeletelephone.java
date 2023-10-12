package com.phonestore.catalogue.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "modeletelephone", schema = "phonestore")
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
	@JoinColumn(name = "marque_id")
	Marque marque;
	
	
	 
	 @OneToMany(mappedBy = "modeletelephone",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
		List<Associationmodelereparation> associations;


	

}
