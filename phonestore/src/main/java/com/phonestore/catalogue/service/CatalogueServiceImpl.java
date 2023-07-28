package com.phonestore.catalogue.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.phonestore.catalogue.dao.AssociationmodelereparationDAO;
import com.phonestore.catalogue.dao.MarqueDAO;
import com.phonestore.catalogue.dao.ModeletelephoneDAO;
import com.phonestore.catalogue.dao.ReparationDAO;
import com.phonestore.catalogue.domain.Marque;
import com.phonestore.catalogue.domain.Modeletelephone;
import com.phonestore.catalogue.dto.AssociationmodelereparationDTO;
import com.phonestore.catalogue.dto.MarqueDTO;
import com.phonestore.catalogue.dto.ModeletelephoneCreationDTO;
import com.phonestore.catalogue.dto.ModeletelephoneDTO;
import com.phonestore.catalogue.dto.ModeletelephoneUpdatedDTO;
import com.phonestore.catalogue.dto.ReparationDTO;
import com.phonestore.catalogue.exception.IdMarqueNonExistanteException;
import com.phonestore.catalogue.exception.IdModeleNonExistantException;
import com.phonestore.catalogue.exception.ReferenceModeleExistanteException;

import jakarta.validation.Valid;

@Service
@Validated
@Transactional
public class CatalogueServiceImpl implements CatalogueService {

	@Autowired
	ModeletelephoneDAO modeletelephoneDAO;

	@Autowired
	MarqueDAO marqueDAO;
	
	@Autowired
	ReparationDAO reparationDAO;
	
	@Autowired
	AssociationmodelereparationDAO associationmodelereparationDAO;

	@Override
	public List<MarqueDTO> findMarques() {
		return marqueDAO.findAll().stream().map(CatalogueDTOMapper::marqueToDTO).toList();
	}
	
	@Override
	public List<ReparationDTO> findReparations() {
		return reparationDAO.findAll().stream().map(CatalogueDTOMapper::reparationToDTO).toList();
	}
	
	@Override
	public List<ReparationDTO> findReparationsNonPratiquees(Long modeletephone_id) {
		return reparationDAO.findReparationNonPratiquees(modeletephone_id).stream().map(CatalogueDTOMapper::reparationToDTO).toList();
	}

	@Override
	public List<ModeletelephoneDTO> findModeletelephones() {

		return modeletelephoneDAO.findByPrixAsc().stream().map(CatalogueDTOMapper::modeletelephoneToDTO).toList();
	}

	@Override
	public List<ModeletelephoneDTO> findModeletelephonesByMarque(Long marqueId) {
		Optional<Marque> marque = marqueDAO.findById(marqueId);

		if (marque.isEmpty())
			throw new IdMarqueNonExistanteException();

		return modeletelephoneDAO.findByMarque(marque.get()).stream().map(CatalogueDTOMapper::modeletelephoneToDTO)
				.toList();
	}

	@Override
	public List<ModeletelephoneDTO> findModeletelephonesByTailleEcran(double taille) {

		return modeletelephoneDAO.findByTailleEcran(taille).stream().map(CatalogueDTOMapper::modeletelephoneToDTO)
				.toList();
	}

	@Override
	public List<ModeletelephoneDTO> findModeletelephonesByCapaciteStockage(int capacite) {

		return modeletelephoneDAO.findByCapaciteStockage(capacite).stream()
				.map(CatalogueDTOMapper::modeletelephoneToDTO).toList();
	}

	@Override
	public Optional<ModeletelephoneDTO> findModele(Long id) {
		
		Optional<Modeletelephone> modeletelephone=modeletelephoneDAO.findById(id);
		
		if(modeletelephone.isEmpty())
			throw new IdModeleNonExistantException();
		
		return modeletelephone.map(CatalogueDTOMapper::modeletelephoneToDTO);
	}

	@Override
	public Long createModele(@Valid ModeletelephoneCreationDTO modeletelephoneCreationDTO) {

		List<Modeletelephone> list = modeletelephoneDAO.findByReference(modeletelephoneCreationDTO.getReference().toLowerCase());

		if (!list.isEmpty())
			throw new ReferenceModeleExistanteException();

		Optional<Marque> optMarque = marqueDAO.findById(modeletelephoneCreationDTO.getMarqueDTO().getId());

		if (optMarque.isEmpty())
			throw new IdMarqueNonExistanteException();

		Modeletelephone modele = CatalogueDTOMapper.fromDTOtoModeletelephone(modeletelephoneCreationDTO);
		modele.setMarque(optMarque.get());

		Modeletelephone modeleSaved = modeletelephoneDAO.save(modele);
		return modeleSaved.getId();

	}

	@Override
	public Long updateModele(@Valid ModeletelephoneUpdatedDTO modeletelephoneUpdatedDTO) {

		Optional<Modeletelephone> optModele = modeletelephoneDAO.findById(modeletelephoneUpdatedDTO.getId());
		if (optModele.isEmpty())
			throw new IdModeleNonExistantException();

		Optional<Marque> optMarque = marqueDAO.findById(modeletelephoneUpdatedDTO.getMarqueDTO().getId());

		if (optMarque.isEmpty())
			throw new IdMarqueNonExistanteException();

		List<Modeletelephone> list = modeletelephoneDAO.findByReference(modeletelephoneUpdatedDTO.getReference().toLowerCase());
		for(Modeletelephone modele : list) {
			
			if((modeletelephoneUpdatedDTO.getReference().toLowerCase().equals(modele.getReference()) && modeletelephoneUpdatedDTO.getId()!=modele.getId()))
			throw new ReferenceModeleExistanteException();
			
			//on accepte que la reference soit deja presente que si on parle bien de maj du meme modele
			
		}

		Modeletelephone modele = optModele.get();
		modele.setReference(modeletelephoneUpdatedDTO.getReference().toLowerCase());
		modele.setPrix(modeletelephoneUpdatedDTO.getPrix());
		modele.setTailleEcran(modeletelephoneUpdatedDTO.getTailleEcran());
		modele.setCapaciteStockage(modeletelephoneUpdatedDTO.getCapaciteStockage());
		modele.setImagePath(modeletelephoneUpdatedDTO.getReference().toLowerCase() + ".jpg");
		modele.setMarque(optMarque.get());
		
		Modeletelephone modeleSaved = modeletelephoneDAO.save(modele);
		return modeleSaved.getId();

	}

	@Override
	public List<ModeletelephoneDTO> findModeletelephonesByPrix(double min, double max) {
		
		return modeletelephoneDAO.findByPrix(min, max).stream()
				.map(CatalogueDTOMapper::modeletelephoneToDTO).toList();
	}

	@Override
	public List<ModeletelephoneDTO> findModeletelephoneByReference(String reference) {
		return modeletelephoneDAO.findByReference(reference).stream()
				.map(CatalogueDTOMapper::modeletelephoneToDTO).toList();
	}

	@Override
	public List<ModeletelephoneDTO> findModeletelephonesByTailleEcranRange(double min, double max) {

		return modeletelephoneDAO.findByTailleEcranRange(min, max).stream()
				.map(CatalogueDTOMapper::modeletelephoneToDTO).toList();
	}

	@Override
	public List<AssociationmodelereparationDTO> findAssociationmodelereparationByModele(Long modeletephone_id) {
		
		Optional<Modeletelephone> modeletephone = modeletelephoneDAO.findById(modeletephone_id);

		if (modeletephone.isEmpty())
			throw new IdModeleNonExistantException();

		return associationmodelereparationDAO.findByModeletelephone(modeletephone.get()).stream().map(CatalogueDTOMapper::associationmodelereparationToDTO)
				.toList();
	}

	



}
