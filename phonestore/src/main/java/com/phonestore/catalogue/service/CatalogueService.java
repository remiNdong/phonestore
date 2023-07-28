package com.phonestore.catalogue.service;

import java.util.List;
import java.util.Optional;

import com.phonestore.catalogue.domain.Modeletelephone;
import com.phonestore.catalogue.dto.AssociationmodelereparationDTO;
import com.phonestore.catalogue.dto.MarqueDTO;
import com.phonestore.catalogue.dto.ModeletelephoneDTO;
import com.phonestore.catalogue.dto.ModeletelephoneUpdatedDTO;
import com.phonestore.catalogue.dto.ReparationDTO;
import com.phonestore.catalogue.dto.ModeletelephoneCreationDTO;
import jakarta.validation.Valid;

public interface CatalogueService {
	
	
	
	/*
	 * renvoie la liste des marqueDTO
	 * sera utilisé pour afficher les marques dans un menu deroulant 
	 * et trier par marque
	 */
	List<MarqueDTO> findMarques();
	
	
	/*
	 * renvoie la liste des reparationDTO
	 */
	List<ReparationDTO> findReparations();
	
	/*
	 * renvoie la liste des reparationDTO qui ne sont pas encore pratiquees sur un modele
	 */
	List<ReparationDTO> findReparationsNonPratiquees(Long modeletephone_id);
	
	/*
	 * renvoie la liste des associationsmodelereparationDTO pour un modele
	 */
	List<AssociationmodelereparationDTO> findAssociationmodelereparationByModele(Long modeletephone_id);
	
	
	/*
	 * renverra la liste des modeletelephoneDTO triées par defaut par prix croissant
	 */
	List<ModeletelephoneDTO> findModeletelephones();
	
	/*
	 * renverra la liste des modeletelephoneDTO portant une reference, en theorie il n'y en a que un
	 */
	List<ModeletelephoneDTO> findModeletelephoneByReference(String reference);
	
	/*
	 * renverra un modeleDTO trouvé par son ID
	 */
	Optional<ModeletelephoneDTO> findModele(Long id);
	
	/*
	 * renverra la liste des modeletelephoneDTO filtrée par Marque triées par defaut par prix croissant
	 */
	List<ModeletelephoneDTO> findModeletelephonesByMarque(Long marqueId);
	
	/*
	 * renverra la liste des modeletelephoneDTO triées par taille d'ecran par defaut par prix croissant
	 */
	List<ModeletelephoneDTO> findModeletelephonesByTailleEcran(double taille);
	
	/*
	 * renverra la liste des modeletelephoneDTO triées par capacite de stockage par defaut par prix croissant
	 */
	List<ModeletelephoneDTO> findModeletelephonesByCapaciteStockage(int capacite);
	
	
	/*
	 * renverra la liste des modeletelephoneDTO dans une certaine fourchette de prix par defaut par prix croissant
	 */
	List<ModeletelephoneDTO> findModeletelephonesByPrix(double min, double max);
	
	/*
	 * renverra la liste des modeletelephoneDTO dans une certaine fourchette de taille d'ecran par defaut par prix croissant
	 */
	List<ModeletelephoneDTO> findModeletelephonesByTailleEcranRange(double min, double max);
	
	/*
	 * creera un nouveau modele conforme aux contraintes et renverra son id
	 */
	Long createModele(@Valid ModeletelephoneCreationDTO modeletelephoneCreationDTO);
	
	/*
	 * mise a jour d'un modele conforme aux contraintes et renverra son id
	 */
	Long updateModele(@Valid ModeletelephoneUpdatedDTO modeletelephoneUpdatedDTO);
	

	
	

}
