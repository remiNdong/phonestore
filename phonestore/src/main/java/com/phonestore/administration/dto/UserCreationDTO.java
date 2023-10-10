package com.phonestore.administration.dto;



import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationDTO {

	@NotBlank
	private String username;

	@NotBlank
	private String prenom;

	@NotBlank
	private String nom;

	@NotBlank
	private String numeroTel;

	@NotBlank
	private String password1;

	@NotBlank
	private String password2;

	

}