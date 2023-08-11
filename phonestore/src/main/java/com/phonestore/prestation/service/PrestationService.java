package com.phonestore.prestation.service;

import java.util.List;
import java.util.Optional;

import com.phonestore.catalogue.dto.AssociationmodelereparationDTO;
import com.phonestore.catalogue.dto.ModeletelephoneCreationDTO;
import com.phonestore.catalogue.dto.ReparationDTO;
import com.phonestore.prestation.dto.PrestationCreationDTO;
import com.phonestore.prestation.dto.PrestationDTO;

import jakarta.validation.Valid;

public interface PrestationService {
	
	
	
	/*
	 * renvoie la liste des prestationDTO pour un usager
	 */
	List<PrestationDTO> findPrestationByIdentifiantUsager(String nomUsager);
	
	
	/*
	 * renvoie la liste des prestationDTO 
	 */
	List<PrestationDTO> findAll();
	
	
	/*
	 * renvoie la liste des prestationDTO  par numero de Serie
	 */
	List<PrestationDTO> findPrestationByNumeroSerie(String numeroSerie);
	
	
	/*
	 * renvoie une prestationDTO dans une liste
	 */
	
	List<PrestationDTO> findPrestationByIdList(Long idPrestation);
	
	
	/*
	 * renvoie une prestationDTO 
	 */
	
	Optional<PrestationDTO> findPrestationById(Long idPrestation);
	
	/*
	 * creera une nouvelle prestation conforme aux contraintes et renverra son id
	 */
	Long createPrestation(@Valid PrestationCreationDTO prestationCreationDTO);
	
	/*
	 * mise a jour d'une prestatiion conforme aux contraintes et renverra son id
	 */
	Long updatePrestation(@Valid PrestationDTO prestationDTO);
	

}
