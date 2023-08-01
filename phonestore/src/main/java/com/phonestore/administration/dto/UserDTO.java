package com.phonestore.administration.dto;

import java.util.List;



import com.phonestore.administration.domain.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private Long id;
	
	@Email
	private String username;
	

	@NotBlank
	private String prenom;
	
	@NotBlank
	private String nom;
	
	@NotBlank
	private String numeroTel;
	
	@NotNull
	private List<Role> roles;
	

}
