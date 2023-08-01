package com.phonestore.administration.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phonestore.administration.domain.Role;

public interface RoleDAO extends JpaRepository<Role, Long> {
	
	Role findByRole(String role);
}
