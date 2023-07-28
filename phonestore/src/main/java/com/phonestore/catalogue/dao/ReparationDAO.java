package com.phonestore.catalogue.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.phonestore.catalogue.domain.Reparation;


public interface ReparationDAO extends JpaRepository<Reparation, Long> {
	
	@Query("select r from Reparation r  WHERE r not in (select a.reparation from Associationmodelereparation a where a.modeletelephone.id=:idModele)")
	List<Reparation> findReparationNonPratiquees(@Param("idModele")long idModele);

}
