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

	/*
	 * Methode qui correspond à la recherche d'un usager
	 * Throws UserNonUsagerException si l'utilisateur n'est pas un usager
	 */
	@Override
	public UserDTO findUserDTOByUsername(String username) {
		UserDTO userDTO = UserDTOMapper.UsertoUserDTO(userDAO.findByUsername(username));

		//role dont id est 3 est le role d'un simple usager
		Role roleUser = roleDAO.findById(3L).get();

		if (!userDTO.getRoles().contains(roleUser))
			throw new UserNonUsagerException();

		return userDTO;
	}

	/*
	 * Methode qui correspond à la recherche d'une liste d' usagers
	 * portant un certain nom
	 */
	@Override
	public List<UserDTO> findUserDTOByNom(String nom) {

		

		Role roleUser = roleDAO.findById(3L).get();

		//les utilisateurs ne sont ajoutés à la liste que s'ils sont usagers
		return userDAO.findByNom(nom).stream().filter(u -> u.getRoles().contains(roleUser))
				.map(UserDTOMapper::UsertoUserDTO).toList();

	}

	/*
	 * Methode qui correspond à la recherche d'une liste de tous les usagers
	 * 
	 */
	@Override
	public List<UserDTO> findAll() {



		Role roleUser = roleDAO.findById(3L).get();

		return userDAO.findAll().stream().filter(u -> u.getRoles().contains(roleUser)).map(UserDTOMapper::UsertoUserDTO)
				.toList();

	}

	/*
	 * Methode qui correspond à la recherche d'un seul usager
	 * trouvé par son username et renvoyé sous forme de liste
	 * car dans le front end on travaillera avec une liste
	 */
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

		//role dont id est 2 est le role d'un employe
		Role roleUser = roleDAO.findById(2L).get();

		return userDAO.findAll().stream().filter(u -> u.getRoles().contains(roleUser)).map(UserDTOMapper::UsertoUserDTO)
				.toList();
		
	}

}
