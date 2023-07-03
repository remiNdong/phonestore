package com.phonestore.catalogue.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.phonestore.catalogue.domain.Marque;
import com.phonestore.catalogue.domain.Modeletelephone;

public interface ModeletelephoneDAO extends JpaRepository<Modeletelephone, Long> {
	
	@Query("select m from Modeletelephone m where m.tailleEcran=:taille order by m.prix ASC")
	List<Modeletelephone> findByTailleEcran(@Param("taille")double taille);
	
	@Query("select m from Modeletelephone m where m.capaciteStockage=:capacite order by m.prix ASC")
	List<Modeletelephone> findByCapaciteStockage(@Param("capacite")int capacite);
	
	List<Modeletelephone> findByReference(String ref);
	
	@Query("select m from Modeletelephone m where m.marque=:marque order by m.prix ASC")
	List<Modeletelephone> findByMarque(@Param("marque")Marque marque);
	
	@Query("select m from Modeletelephone m where m.prix between :min and :max ")
	List<Modeletelephone> findByPrix(@Param("min")double min , @Param("max")double max);
	
	@Query("select m from Modeletelephone m order by m.prix ASC")
	List<Modeletelephone> findByPrixAsc();


}
