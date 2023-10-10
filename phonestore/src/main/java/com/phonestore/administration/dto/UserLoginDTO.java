package com.phonestore.administration.dto;




import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
	
	@Email
	private String username;
	
	
	
	@NotBlank
	private String password;

}