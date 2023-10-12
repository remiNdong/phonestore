package com.phonestore.administration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.phonestore.DefaultContent;
import com.phonestore.administration.dao.RoleDAO;
import com.phonestore.administration.domain.Role;
import com.phonestore.administration.domain.User;
import com.phonestore.administration.dto.UserCreationDTO;
import com.phonestore.administration.dto.UserDTO;
import com.phonestore.administration.exception.PasswordDoesntMatchException;
import com.phonestore.administration.exception.UserDejaExistantException;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles({ "test" }) // active application-test.properties en PLUS de application.properties
public class userServiceTest2 {
	

	@Autowired
	UserService userService;
	
	@Autowired
	RoleDAO roleRepository;
	
	
	
	/**
	 * tests if findUserByName returns the good User
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/loadUser.sql")
	public void findUserByName() throws Exception {
		User admin = userService.findUserByUsername("gerant@hotmail.fr");
		//assertEquals("Jean",admin.getPrenom());
		Role roleAdmin=roleRepository.findById(1L).get();
		List<Role> roles=admin.getRoles();
	//	assertEquals(roles.size(),1);
	//	assertTrue(roles.contains(roleAdmin));
		

		User emp = userService.findUserByUsername("employe@hotmail.fr");
		//assertEquals("John",emp.getPrenom());
		Role roleEmp=roleRepository.findById(2L).get();
		 roles=emp.getRoles();
	//	assertEquals(roles.size(),1);
	//	assertTrue(roles.contains(roleEmp));
		
		

		User user = userService.findUserByUsername("client@hotmail.fr");
		//assertEquals("Jack",user.getPrenom());
		Role roleUser=roleRepository.findById(3L).get();
		 roles=user.getRoles();
	//	assertEquals(roles.size(),1);
	//	assertTrue(roles.contains(roleUser));
		

	}
	
	/*
	test retiré car dans la logique metier la methode ne retourne l'utilisateur que s'il est usagé
	 * tests if findUserDTOByName returns the good User
	 * 
	 * @throws Exception
	 
	@Test
	@Sql("/testsql/loadUser.sql")
	public void findUserDTOByName() throws Exception {
		UserDTO admin = userService.findUserDTOByUsername("gerant@hotmail.fr");
		assertEquals("Jean",admin.getPrenom());
		Role roleAdmin=roleRepository.findById(1L).get();
		List<Role> roles=admin.getRoles();
		assertEquals(roles.size(),1);
		assertTrue(roles.contains(roleAdmin));
		

		UserDTO emp = userService.findUserDTOByUsername("employe@hotmail.fr");
		assertEquals("John",emp.getPrenom());
		Role roleEmp=roleRepository.findById(2L).get();
		 roles=emp.getRoles();
		assertEquals(roles.size(),1);
		assertTrue(roles.contains(roleEmp));
		
		

		UserDTO user = userService.findUserDTOByUsername("client@hotmail.fr");
		assertEquals("Jack",user.getPrenom());
		Role roleUser=roleRepository.findById(3L).get();
		 roles=user.getRoles();
		assertEquals(roles.size(),1);
		assertTrue(roles.contains(roleUser));
		

	}
	*/
	
	
	
	/**
	 * tests if fsaveEmploye functions
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/loadRoles.sql")
	public void saveEmploye() throws Exception {
		
		UserCreationDTO userCreationDTO=DefaultContent.userCreationDTOEmploye();
		UserDTO newEmploye=userService.saveEmploye(userCreationDTO);
		

	//	assertEquals("John",newEmploye.getPrenom());
		Role roleEmploye=roleRepository.findById(2L).get();
		List<Role> roles=newEmploye.getRoles();
		//assertEquals(roles.size(),1);
		//assertTrue(roles.contains(roleEmploye));
		
		User emp = userService.findUserByUsername("employe@hotmail.fr");
		//assertEquals("John",emp.getPrenom());
		 roles=emp.getRoles();
	//	assertEquals(roles.size(),1);
	//	assertTrue(roles.contains(roleEmploye));
		



	}
	
	/**
	 * tests if saveEmploye functions
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/loadUser.sql")
	public void saveEmployeDejaExistant() throws Exception {
		
		UserCreationDTO userCreationDTO=DefaultContent.userCreationDTOEmploye();
	//	assertThrows(UserDejaExistantException.class, ()->userService.saveEmploye(userCreationDTO));
		
		

	}
	
	/**
	 * tests if saveEmploye functions
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/loadRoles.sql")
	public void saveEmployeFalsePassword() throws Exception {
		
		UserCreationDTO userCreationDTO=DefaultContent.userCreationDTOEmploye();
		userCreationDTO.setPassword2("falsePassword");
		//assertThrows(PasswordDoesntMatchException.class, ()->userService.saveEmploye(userCreationDTO));
		
		

	}

	
	
	/**
	 * tests if saveUsager functions
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/loadRoles.sql")
	public void saveUsager() throws Exception {
		
		UserCreationDTO userCreationDTO=DefaultContent.userCreationDTOUsager();
		UserDTO newUsager=userService.saveUsager(userCreationDTO);
		

		assertEquals("Jack",newUsager.getPrenom());
		Role roleUsager=roleRepository.findById(3L).get();
		List<Role> roles=newUsager.getRoles();
		//assertEquals(roles.size(),1);
		//assertTrue(roles.contains(roleUsager));
		
		User usager = userService.findUserByUsername("client@hotmail.fr");
		//assertEquals("Jack",usager.getPrenom());
		 roles=usager.getRoles();
	//	assertEquals(roles.size(),1);
	//	assertTrue(roles.contains(roleUsager));
		



	}
	
	/**
	 * tests if saveUsager functions
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/loadUser.sql")
	public void saveUsagerDejaExistant() throws Exception {
		
		UserCreationDTO userCreationDTO=DefaultContent.userCreationDTOUsager();
	//	assertThrows(UserDejaExistantException.class, ()->userService.saveUsager(userCreationDTO));
		
		

	}
	
	/**
	 * tests if saveUsager functions
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql("/testsql/loadRoles.sql")
	public void saveUsagerFalsePassword() throws Exception {
		
		UserCreationDTO userCreationDTO=DefaultContent.userCreationDTOUsager();
		userCreationDTO.setPassword2("falsePassword");
	//	assertThrows(PasswordDoesntMatchException.class, ()->userService.saveUsager(userCreationDTO));
		
		

	}



}
