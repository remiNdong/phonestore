package com.phonestore.administration.domain;

import java.util.List;

import com.phonestore.catalogue.domain.Associationmodelereparation;
import com.phonestore.prestation.domain.Prestation;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "phonestore")
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;

	@Column(unique = true)
	private String username;
	
	private String prenom;
	
	private String nom;
	
	private String numeroTel;

	private String password;
	

	  @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
		@JoinTable(name="user_role",joinColumns = @JoinColumn(name="user_id") , 
				   inverseJoinColumns = @JoinColumn(name="role_id")) 
	private List<Role> roles;
	  
	  @OneToMany(mappedBy = "usager",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
		List<Prestation> prestation;

}
