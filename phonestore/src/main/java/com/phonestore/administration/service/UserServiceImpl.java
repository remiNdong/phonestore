package com.phonestore.administration.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.phonestore.administration.dao.RoleDAO;
import com.phonestore.administration.dao.UserDAO;
import com.phonestore.administration.domain.Role;
import com.phonestore.administration.domain.User;
import com.phonestore.administration.dto.UserCreationDTO;
import com.phonestore.administration.dto.UserDTO;
import com.phonestore.administration.exception.PasswordDoesntMatchException;
import com.phonestore.administration.exception.UserDejaExistantException;
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
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	/*
	 * Methode qui correspond à la recherche d'un usager Throws
	 * UserNonUsagerException si l'utilisateur n'est pas un usager
	 */
	@Override
	public UserDTO findUserDTOByUsername(String username) {
		UserDTO userDTO = UserDTOMapper.UsertoUserDTO(userDAO.findByUsername(username));

		// role dont id est 3 est le role d'un simple usager
		Role roleUser = roleDAO.findById(3L).get();

		if (!userDTO.getRoles().contains(roleUser))
			throw new UserNonUsagerException();

		return userDTO;
	}
	
	@Override
	public UserDTO findUserAllTypeDTOByUsername(String username) {

		return UserDTOMapper.UsertoUserDTO(userDAO.findByUsername(username));
	}

	/*
	 * Methode qui correspond à la recherche d'une liste d' usagers portant un
	 * certain nom
	 */
	@Override
	public List<UserDTO> findUserDTOByNom(String nom) {

		Role roleUser = roleDAO.findById(3L).get();

		// les utilisateurs ne sont ajoutés à la liste que s'ils sont usagers
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
	 * Methode qui correspond à la recherche d'un seul usager trouvé par son
	 * username et renvoyé sous forme de liste car dans le front end on travaillera
	 * avec une liste
	 */
	@Override
	public List<UserDTO> findUserDTOByUsernameList(String username) {
		List<UserDTO> list = new ArrayList<UserDTO>();

		User user = userDAO.findByUsername(username);

		if (user != null) {
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

		// role dont id est 2 est le role d'un employe
		Role roleUser = roleDAO.findById(2L).get();

		return userDAO.findAll().stream().filter(u -> u.getRoles().contains(roleUser)).map(UserDTOMapper::UsertoUserDTO)
				.toList();

	}

	@Override
	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userDAO.save(user);
	}

	@Override
	public UserDTO saveUsager(UserCreationDTO userCreationDTO) {

		if (userDAO.findByUsername(userCreationDTO.getUsername()) != null)
			throw new UserDejaExistantException();

		if (!userCreationDTO.getPassword1().equals(userCreationDTO.getPassword2()))
			throw new PasswordDoesntMatchException();

		// le role N°3 sera celui d'Usager
		Role roleUsager = roleDAO.findById(3L).get();
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleUsager);

		User usager = UserDTOMapper.UserCreationDTOtoUsager(userCreationDTO);
		usager.setRoles(roles);
		usager.setPassword(bCryptPasswordEncoder.encode(userCreationDTO.getPassword1()));
		userDAO.save(usager);

		return UserDTOMapper.UsertoUserDTO(usager);
	}

	@Override
	public UserDTO saveEmploye(UserCreationDTO userCreationDTO) {
		if (userDAO.findByUsername(userCreationDTO.getUsername()) != null)
			throw new UserDejaExistantException();

		if (!userCreationDTO.getPassword1().equals(userCreationDTO.getPassword2()))
			throw new PasswordDoesntMatchException();

		// le role N°2 sera celui d'employe
		Role roleEmploye = roleDAO.findById(2L).get();
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleEmploye);

		User employe = UserDTOMapper.UserCreationDTOtoUsager(userCreationDTO);
		employe.setRoles(roles);
		employe.setPassword(bCryptPasswordEncoder.encode(userCreationDTO.getPassword1()));
		userDAO.save(employe);

		return UserDTOMapper.UsertoUserDTO(employe);
	}

	@Override
	public User findUserByUsername(String username) {

		return userDAO.findByUsername(username);
	}

	

	/*
	 * A ete utilisee qu'au premier lancement pour creer les premiers User
	 */
	@Override
	public Role addRole(Role role) {

		return roleDAO.save(role);
	}

	/*
	 * A ete utilisee qu'au premier lancement pour creer les premiers User
	 */
	@Override
	public User addRoleToUser(String username, String rolename) {

		User usr = userDAO.findByUsername(username);
		Role r = roleDAO.findByRole(rolename);
		usr.getRoles().add(r);
		return usr;
	}

	@Override
	public List<User> findAllUsers() {

		return userDAO.findAll();
	}

	@Override
	public User findById(Long id) {

		return userDAO.findById(id).get();
	}

}
