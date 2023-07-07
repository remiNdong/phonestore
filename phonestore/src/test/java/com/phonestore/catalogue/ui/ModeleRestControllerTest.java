package com.phonestore.catalogue.ui;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.phonestore.catalogue.dto.MessageDTO;



public class ModeleRestControllerTest {

	@Test
	public void testcreateModele() {

		// dans ce telephone seul le prix est faux
		String modeletelephoneFalse = "{\"id\": 0 , \"reference\" : \"iphone_2000\", \"prix\": 0,  \"tailleEcran\": 6.1, \"capaciteStockage\": 128, \"marqueDTO\":{\"id\":1,\"nom\":\"APPLE\"}}";

		WebClient client = WebClient.create("http://localhost:8080");
		
		
		MessageDTO messageDTO =client
		.post()
		.uri("/api/modeles/addmodele")
		.contentType(MediaType.APPLICATION_JSON) 
		.bodyValue(modeletelephoneFalse)
		.retrieve() // expédition...
		.bodyToMono(MessageDTO.class) // type attendu
		.block(); // opération bloquante...
		
		System.out.println(messageDTO.getMessage());
		
		assertTrue(messageDTO.getMessage().equals("{prix=doit être supérieur ou égal à 1}"));
		
	}

}
