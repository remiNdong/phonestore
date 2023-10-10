package com.phonestore.administration.service;

import com.phonestore.administration.domain.User;
import com.phonestore.administration.dto.UserCreationDTO;
import com.phonestore.administration.dto.UserDTO;

public class UserDTOMapper {
	

	
	public static UserDTO UsertoUserDTO(User user) {
		
		return new UserDTO(user.getUser_id(), user.getUsername(), user.getPrenom(), user.getNom(), user.getNumeroTel(), user.getRoles());
	}
	
	public static User UserCreationDTOtoUsager(UserCreationDTO userCreationDTO) {
		
		return new User(0L, userCreationDTO.getUsername(), userCreationDTO.getPrenom(), userCreationDTO.getNom(), userCreationDTO.getNumeroTel(), userCreationDTO.getPassword1(),null,null );
			
		
	}
	


}
