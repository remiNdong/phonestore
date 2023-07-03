package com.phonestore.catalogue.service;

import java.util.List;

import com.phonestore.catalogue.dto.MarqueDTO;
import com.phonestore.catalogue.dto.ModeletelephoneDTO;

public interface CatalogueService {
	
	
	
	/*
	 * renvoie la liste des marqueDTO
	 * sera utilisé pour afficher les marques dans un menu deroulant 
	 * et trier par marque
	 */
	List<MarqueDTO> findMarques();
	
	
	/*
	 * renverra la liste des modeletelephoneDTO triées par defaut par prix croissant
	 */
	List<ModeletelephoneDTO> findModeletelephones();
	
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
	

}
