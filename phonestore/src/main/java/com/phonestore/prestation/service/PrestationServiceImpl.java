package com.phonestore.prestation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.phonestore.administration.dao.RoleDAO;
import com.phonestore.administration.dao.UserDAO;
import com.phonestore.administration.domain.Role;
import com.phonestore.administration.domain.User;
import com.phonestore.administration.exception.UserNonUsagerException;
import com.phonestore.catalogue.dao.AssociationmodelereparationDAO;
import com.phonestore.catalogue.dao.ModeletelephoneDAO;
import com.phonestore.catalogue.domain.Associationmodelereparation;
import com.phonestore.catalogue.domain.Modeletelephone;

import com.phonestore.catalogue.exception.AssociationmodeletelephonereparationNonExistanteException;

import com.phonestore.catalogue.exception.IdModeleNonExistantException;

import com.phonestore.prestation.dao.PrestationDAO;
import com.phonestore.prestation.domain.Prestation;
import com.phonestore.prestation.dto.PrestationCreationDTO;
import com.phonestore.prestation.dto.PrestationDTO;
import com.phonestore.prestation.exception.PrestationNonExistanteException;
import com.phonestore.prestation.exception.UserNonExistantException;

import jakarta.validation.Valid;

@Service
@Validated
@Transactional
public class PrestationServiceImpl implements PrestationService {

	@Autowired
	PrestationDAO prestationDAO;
	
	@Autowired
	ModeletelephoneDAO modeletelephoneDAO;
	
	@Autowired
	AssociationmodelereparationDAO associationmodelereparationDAO;

	@Autowired
	UserDAO userDAO;
	

	
	@Autowired
	RoleDAO roleDAO;

	@Override
	public Optional<PrestationDTO> findPrestationById(Long idPrestation) {

		Optional<Prestation> prestation = prestationDAO.findById(idPrestation);
		if (prestation.isEmpty())
			throw new PrestationNonExistanteException();

		return prestation.map(PrestationDTOMapper::prestationToDTO);
	}

	@Override
	public List<PrestationDTO> findPrestationByIdentifiantUsager(String identifiantUsager) {
		User user = userDAO.findByUsername(identifiantUsager);

		if (user == null)
			throw new UserNonExistantException();
		
		

		Role roleUser = roleDAO.findById(3L).get();

		if (!user.getRoles().contains(roleUser))
			throw new UserNonUsagerException();

		return prestationDAO.findByUsager(user).stream().map(PrestationDTOMapper::prestationToDTO).toList();
	}

	@Override
	public Long createPrestation(@Valid PrestationCreationDTO prestationCreationDTO) {
		
		Optional<Modeletelephone> optModele = modeletelephoneDAO.findById(prestationCreationDTO.getIdModele());

		if (optModele.isEmpty())
			throw new IdModeleNonExistantException();
		
		Optional<Associationmodelereparation> optAssociation = associationmodelereparationDAO.findById(prestationCreationDTO.getIdAssociation());

		if (optAssociation.isEmpty())
			throw new AssociationmodeletelephonereparationNonExistanteException();
		
		User user = userDAO.findByUsername(prestationCreationDTO.getIdentifiantUsager());

		if (user == null)
			throw new UserNonExistantException();

		Role roleUser = roleDAO.findById(3L).get();

		if (!user.getRoles().contains(roleUser))
			throw new UserNonUsagerException();
		
		

		Prestation prestation = PrestationDTOMapper.fromDTOtoPrestation(prestationCreationDTO);
		prestation.setAssociation(optAssociation.get());
		prestation.setUsager(user);

		Prestation prestationSaved = prestationDAO.save(prestation);
		return prestationSaved.getId();
	}

	/*
	 * Methode qui permet de modifier le prix ou le statut de la prestation uniquement
	 */
	@Override
	public Long updatePrestation(@Valid PrestationDTO prestationDTO) {

		Optional<Prestation> presta=prestationDAO.findById(prestationDTO.getId());
		if(presta.isEmpty())
			throw new PrestationNonExistanteException();
		
		Optional<Associationmodelereparation> optAssociation = associationmodelereparationDAO.findById(prestationDTO.getIdAssociation());

		if (optAssociation.isEmpty())
			throw new AssociationmodeletelephonereparationNonExistanteException();
		
		User user = userDAO.findByUsername(prestationDTO.getIdentifiantUsager());

		if (user == null)
			throw new UserNonExistantException();

		Role roleUser = roleDAO.findById(3L).get();

		if (!user.getRoles().contains(roleUser))
			throw new UserNonUsagerException();
		
		

		Prestation prestation = PrestationDTOMapper.fromPrestationDTOtoPrestation(prestationDTO);
		prestation.setAssociation(optAssociation.get());
		prestation.setUsager(user);

		Prestation prestationUpdated = prestationDAO.save(prestation);
		return prestationUpdated.getId();
	}

	@Override
	public List<PrestationDTO> findAll() {
		return prestationDAO.findAll().stream().map(PrestationDTOMapper::prestationToDTO).toList();
	}

	@Override
	public List<PrestationDTO> findPrestationByNumeroSerie(String numeroSerie) {
		return prestationDAO.findByNumeroSerie(numeroSerie).stream().map(PrestationDTOMapper::prestationToDTO).toList();
	}

	@Override
	public List<PrestationDTO> findPrestationByIdList(Long idPrestation) {

	List<PrestationDTO> list = new ArrayList<PrestationDTO>();
	
		
		Optional<Prestation>  presta=prestationDAO.findById(idPrestation);
		
		if(!presta.isEmpty()) {
			PrestationDTO prestationDTO = PrestationDTOMapper.prestationToDTO(presta.get());

	
		list.add(prestationDTO);
		
		}

		return list;
	}

}
