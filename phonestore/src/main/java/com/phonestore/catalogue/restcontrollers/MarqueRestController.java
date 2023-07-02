package com.phonestore.catalogue.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phonestore.catalogue.dao.MarqueDAO;
import com.phonestore.catalogue.domain.Marque;

@RestController
@RequestMapping("/api/marques")
public class MarqueRestController {
	
	@Autowired
	MarqueDAO marqueDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Marque> getAllMarques() {
		return marqueDAO.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Marque getMarqueById(@PathVariable("id") Long id) {
		return marqueDAO.findById(id).get();
	}
	
	

}
