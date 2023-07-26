package com.phonestore.catalogue.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phonestore.catalogue.domain.Reparation;


public interface ReparationDAO extends JpaRepository<Reparation, Long> {
	

}
