package com.phonestore.catalogue.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.phonestore.catalogue.dao.MarqueDAO;
import com.phonestore.catalogue.dao.ModeletelephoneDAO;
import com.phonestore.catalogue.domain.Marque;
import com.phonestore.catalogue.dto.MarqueDTO;
import com.phonestore.catalogue.dto.ModeletelephoneDTO;


@Service
@Validated
@Transactional
public class CatalogueServiceImpl implements CatalogueService {

	@Autowired
	ModeletelephoneDAO modeletelephoneDAO;
	
	@Autowired
	MarqueDAO marqueDAO;

	@Override
	public List<MarqueDTO> findMarques() {
		return marqueDAO.findAll().stream().map(CatalogueDTOMapper::marqueToDTO).toList();
	}

	@Override
	public List<ModeletelephoneDTO> findModeletelephones() {
		
		return modeletelephoneDAO.findByPrixAsc().stream().map(CatalogueDTOMapper::modeletelephoneToDTO).toList();
	}

	@Override
	public List<ModeletelephoneDTO> findModeletelephonesByMarque(Long marqueId) {
		Optional<Marque> marque=marqueDAO.findById(marqueId);
		
		
		return modeletelephoneDAO.findByMarque(marque.get()).stream().map(CatalogueDTOMapper::modeletelephoneToDTO).toList();
	}

	@Override
	public List<ModeletelephoneDTO> findModeletelephonesByTailleEcran(double taille) {


		return modeletelephoneDAO.findByTailleEcran(taille).stream().map(CatalogueDTOMapper::modeletelephoneToDTO).toList();
	}

	@Override
	public List<ModeletelephoneDTO> findModeletelephonesByCapaciteStockage(int capacite) {

		return modeletelephoneDAO.findByCapaciteStockage(capacite).stream().map(CatalogueDTOMapper::modeletelephoneToDTO).toList();
	}

}
