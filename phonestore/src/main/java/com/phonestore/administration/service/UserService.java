package com.phonestore.administration.service;

import java.util.List;

import com.phonestore.administration.dto.UserDTO;

import jakarta.validation.Valid;

public interface UserService {
	
	UserDTO findUserDTOByUsername(String username);
	
	List<UserDTO> findUserDTOByNom(String nom);
	
	List<UserDTO> findAll();
	
	List<UserDTO> findUserDTOByUsernameList(String username);
	
	List<UserDTO> findAllEmployes();
	

}
