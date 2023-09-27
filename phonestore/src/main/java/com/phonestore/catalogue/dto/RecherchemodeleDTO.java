package com.phonestore.catalogue.dto;

import java.util.List;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecherchemodeleDTO {
	
	
	private Long idMarque;
	
	
	private int rangeTailleEcran;
	

	private int capaciteStockage;
	

	private int rangeprix;
	

}
