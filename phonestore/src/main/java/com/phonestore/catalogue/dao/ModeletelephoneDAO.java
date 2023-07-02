package com.phonestore.catalogue.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phonestore.catalogue.domain.Marque;
import com.phonestore.catalogue.domain.Modeletelephone;

public interface ModeletelephoneDAO extends JpaRepository<Modeletelephone, Long> {
	
	List<Modeletelephone> findByTailleEcran(double taille);
	List<Modeletelephone> findByCapaciteStockage(int capacite);
	List<Modeletelephone> findByReference(String ref);
	List<Modeletelephone> findByMarque(Marque marque);


}
