package com.phonestore.administration.service;

import com.phonestore.administration.domain.User;
import com.phonestore.administration.dto.UserDTO;

public class UserDTOMapper {
	

	
	public static UserDTO UsertoUserDTO(User user) {
		
		return new UserDTO(user.getUser_id(), user.getUsername(), user.getPrenom(), user.getNom(), user.getNumeroTel(), user.getRoles());
	}


}
