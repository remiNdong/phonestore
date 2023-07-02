package com.phonestore.catalogue.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phonestore.catalogue.domain.Marque;



@Repository
public interface MarqueDAO extends JpaRepository<Marque, Long>{

}
