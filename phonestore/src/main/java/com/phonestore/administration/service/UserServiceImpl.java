package com.phonestore.administration.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.phonestore.administration.dao.RoleDAO;
import com.phonestore.administration.dao.UserDAO;
import com.phonestore.administration.domain.Role;
import com.phonestore.administration.domain.User;
import com.phonestore.administration.dto.UserDTO;
import com.phonestore.administration.exception.UserNonUsagerException;
import com.phonestore.catalogue.service.CatalogueDTOMapper;
import com.phonestore.prestation.exception.UserNonExistantException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Transactional
@Validated
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Autowired
	RoleDAO roleDAO;

	@Override
	public UserDTO findUserDTOByUsername(String username) {
		UserDTO userDTO = UserDTOMapper.UsertoUserDTO(userDAO.findByUsername(username));

		Role roleUser = roleDAO.findById(3L).get();

		if (!userDTO.getRoles().contains(roleUser))
			throw new UserNonUsagerException();

		return userDTO;
	}

	@Override
	public List<UserDTO> findUserDTOByNom(String nom) {

		// return userDAO.findByNom(nom).stream().map(UserDTOMapper::UsertoUserDTO)
		// .toList();

		Role roleUser = roleDAO.findById(3L).get();

		return userDAO.findByNom(nom).stream().filter(u -> u.getRoles().contains(roleUser))
				.map(UserDTOMapper::UsertoUserDTO).toList();

	}

	@Override
	public List<UserDTO> findAll() {
		// return userDAO.findAll().stream().map(UserDTOMapper::UsertoUserDTO)
		// .toList();

		Role roleUser = roleDAO.findById(3L).get();

		return userDAO.findAll().stream().filter(u -> u.getRoles().contains(roleUser)).map(UserDTOMapper::UsertoUserDTO)
				.toList();

	}

	@Override
	public List<UserDTO> findUserDTOByUsernameList(String username) {
		List<UserDTO> list = new ArrayList<UserDTO>();
	
		
		User user=userDAO.findByUsername(username);
		
		if(user!=null) {
		UserDTO userDTO = UserDTOMapper.UsertoUserDTO(userDAO.findByUsername(username));

		Role roleUser = roleDAO.findById(3L).get();

		
		if (!userDTO.getRoles().contains(roleUser))
			throw new UserNonUsagerException();

		
		list.add(userDTO);
		
		}

		return list;
	}

	@Override
	public List<UserDTO> findAllEmployes() {

		Role roleUser = roleDAO.findById(2L).get();

		return userDAO.findAll().stream().filter(u -> u.getRoles().contains(roleUser)).map(UserDTOMapper::UsertoUserDTO)
				.toList();
		
	}

}
