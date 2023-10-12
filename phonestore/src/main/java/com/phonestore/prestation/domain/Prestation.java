package com.phonestore.prestation.domain;



import java.util.Date;

import com.phonestore.administration.domain.User;
import com.phonestore.catalogue.domain.Associationmodelereparation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prestation", schema = "phonestore")
public class Prestation {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@NotNull
	private Long id;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "user_id")
	private User usager;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "association_id")
	private Associationmodelereparation association;
	
	@Column
	@NotBlank
	String numeroSerie;
	
	@Column
	@NotNull
	double prix;
	
	@Column
	@NotNull
	String status;
	
	@Column
	private Date dateCreation;
	
	
	
	
	

}
