package com.phonestore.catalogue.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phonestore.catalogue.domain.Associationmodelereparation;
import com.phonestore.catalogue.domain.Modeletelephone;
import com.phonestore.catalogue.domain.Reparation;

public interface AssociationmodelereparationDAO extends JpaRepository<Associationmodelereparation, Long> {
	
	
	List<Associationmodelereparation> findByModeletelephone(Modeletelephone modeletelephone);
	
	List<Associationmodelereparation> findByModeletelephoneAndReparation(Modeletelephone modeletelephone,Reparation reparation);

}
