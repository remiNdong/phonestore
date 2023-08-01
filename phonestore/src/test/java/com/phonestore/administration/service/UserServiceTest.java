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

import com.phonestore.administration.dao.RoleDAO;
import com.phonestore.administration.domain.Role;
import com.phonestore.administration.dto.UserDTO;
import com.phonestore.administration.exception.UserNonUsagerException;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles({ "test" }) // active application-test.properties en PLUS de application.properties
public class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleDAO roleDAO;
	
	@Test
	@Sql("/testsql/loadUser.sql")
	public void findUserDTOByName() throws Exception {
		
		
		assertThrows(UserNonUsagerException.class, ()-> userService.findUserDTOByUsername("gerant@hotmail.fr"));
		assertThrows(UserNonUsagerException.class, ()-> userService.findUserDTOByUsername("employe@hotmail.fr"));
		


		UserDTO user = userService.findUserDTOByUsername("client@hotmail.fr");
		assertEquals("Jack",user.getPrenom());
		Role roleUser=roleDAO.findById(3L).get();
		 List<Role>roles=user.getRoles();
		assertEquals(roles.size(),1);
		assertTrue(roles.contains(roleUser));
		

	}
	
	@Test
	@Sql("/testsql/loadUser.sql")
	public void findUserDTOByNom() throws Exception {
		
		
		List<UserDTO> list = userService.findUserDTOByNom("Legérant");
		assertTrue(list.size()==0);
	
		
		 list = userService.findUserDTOByNom("Lebosseur");
		assertTrue(list.size()==0);
	
		
		
		 list = userService.findUserDTOByNom("Leclient");
			assertTrue(list.size()==1);
			UserDTO user=list.get(0);
		assertEquals("Jack",user.getPrenom());
		Role roleUser=roleDAO.findById(3L).get();
		 List<Role>roles=user.getRoles();
		assertEquals(roles.size(),1);
		assertTrue(roles.contains(roleUser));
		

	}
	
	
	@Test
	@Sql("/testsql/catalogue/prestation/loadPrestation.sql")
	public void findUserDTOByName2() throws Exception {


		
		

		UserDTO user = userService.findUserDTOByUsername("client@hotmail.fr");
		assertEquals("Jack",user.getPrenom());
		Role roleUser=roleDAO.findById(3L).get();
		 List<Role>roles=user.getRoles();
		assertEquals(roles.size(),1);
		assertTrue(roles.contains(roleUser));
		

	}
	
	@Test
	@Sql("/testsql/loadUser.sql")
	public void findAll() throws Exception {
		
	
		
		List<UserDTO>list = userService.findAll();
		assertTrue(list.size()==1);
		UserDTO user=list.get(0);

		assertEquals("Jack",user.getPrenom());
		Role roleUser=roleDAO.findById(3L).get();
		 List<Role>roles=user.getRoles();
		assertEquals(roles.size(),1);
		assertTrue(roles.contains(roleUser));
		

	}
	
	@Test
	@Sql("/testsql/catalogue/prestation/loadPrestation.sql")
	public void findAll2() throws Exception {
		
	
		
		List<UserDTO>list = userService.findAll();
		assertTrue(list.size()==1);
		UserDTO user=list.get(0);

		assertEquals("Jack",user.getPrenom());
		Role roleUser=roleDAO.findById(3L).get();
		 List<Role>roles=user.getRoles();
		assertEquals(roles.size(),1);
		assertTrue(roles.contains(roleUser));
		

	}
	
	@Test
	@Sql("/testsql/catalogue/prestation/loadPrestation.sql")
	public void findUserDTOByNom2() throws Exception {
		
	
		
		List<UserDTO>list = userService.findUserDTOByNom("Leclient");
		assertTrue(list.size()==1);
		UserDTO user=list.get(0);

		assertEquals("Jack",user.getPrenom());
		Role roleUser=roleDAO.findById(3L).get();
		 List<Role>roles=user.getRoles();
		assertEquals(roles.size(),1);
		assertTrue(roles.contains(roleUser));
		

	}
	

}
