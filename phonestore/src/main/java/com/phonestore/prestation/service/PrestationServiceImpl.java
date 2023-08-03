package com.phonestore.prestation.service;

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
import com.phonestore.catalogue.service.CatalogueDTOMapper;
import com.phonestore.prestation.dao.PrestationDAO;
import com.phonestore.prestation.domain.Prestation;
import com.phonestore.prestation.dto.PrestationDTO;
import com.phonestore.prestation.exception.PrestationNonExistanteException;
import com.phonestore.prestation.exception.UserNonExistantException;

@Service
@Validated
@Transactional
public class PrestationServiceImpl implements PrestationService {

	@Autowired
	PrestationDAO prestationDAO;

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

}
