package com.phonestore.administration.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phonestore.administration.domain.User;

public interface UserDAO extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	
	List<User>findByNom(String nom);
	
	List<User> findByPrenom(String prenom);
}