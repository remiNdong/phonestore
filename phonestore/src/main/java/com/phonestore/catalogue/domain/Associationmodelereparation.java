package com.phonestore.catalogue.domain;

import java.util.List;


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

	@ManyToOne@
	JoinColumn(name = "reparation_id")
	private Reparation reparation;
	
	@Column
	@NotNull
	private double prix;
}
