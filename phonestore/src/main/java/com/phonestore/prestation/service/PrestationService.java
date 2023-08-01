package com.phonestore.prestation.service;

import java.util.List;
import java.util.Optional;

import com.phonestore.catalogue.dto.ReparationDTO;
import com.phonestore.prestation.dto.PrestationDTO;

public interface PrestationService {
	
	
	
	/*
	 * renvoie la liste des prestationDTO pour un usager
	 */
	List<PrestationDTO> findPrestationByIdentifiantUsager(String nomUsager);
	
	/*
	 * renvoie une prestationDTO 
	 */
	
	Optional<PrestationDTO> findPrestationById(Long idPrestation);

}
