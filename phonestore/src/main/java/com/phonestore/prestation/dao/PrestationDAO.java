package com.phonestore.prestation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phonestore.administration.domain.User;
import com.phonestore.prestation.domain.Prestation;

public interface PrestationDAO extends JpaRepository<Prestation, Long>{
	
	List<Prestation> findByUsager(User user);
	

}
