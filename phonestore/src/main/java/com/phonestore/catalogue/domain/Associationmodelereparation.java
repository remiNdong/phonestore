package com.phonestore.catalogue.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*Une Associationmodelereparation associe un modele et un type de reparation
 * pour pratiquer un type de reparation sur un modele
 * il faut au prealable creer cette association
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Associationmodelereparation {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@NotNull
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name = "modeletelephone_id")
	private Modeletelephone modeletelephone;

	@ManyToOne
	@JoinColumn(name = "reparation_id")
	private Reparation reparation;
	
	@Column
	@NotNull
	private double prix;
	
	//par defaut le type de reparation sera praticable sur un modele
	// donc praticable sera egal à 1
	// si le commercant ne pratique plus ce type de reparation sur le modele
	// il passera praticable à 0
	@Column
	private int praticable;
}
