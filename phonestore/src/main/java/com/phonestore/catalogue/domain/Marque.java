package com.phonestore.catalogue.domain;






import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Marque {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@NotNull
	private Long id;
	
	@Column(unique = true)
	@NotNull
	private String nom;
	
	@OneToMany(mappedBy = "marque")
	@JsonIgnore
	List<Modeletelephone> modeles;

}
