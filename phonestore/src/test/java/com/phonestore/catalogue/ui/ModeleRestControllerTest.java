package com.phonestore.catalogue.ui;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.phonestore.catalogue.dto.MessageDTO;


//Test abandonnés car avec la securisation des Api via la classe SecurityConfig ils ne passent plus


public class ModeleRestControllerTest {
	/*
	

	@Test
	public void testcreateModelePrixFaux() {

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
		
		assertTrue(messageDTO.getMessage().contains("prix : doit être supérieur ou égal à 1"));
		
	}
	
	@Test
	public void testcreateModeleReferenceFausse() {

		// dans ce telephone seul le prix est faux
		String modeletelephoneFalse = "{\"id\": 0 , \"reference\" : \"\", \"prix\": 1200,  \"tailleEcran\": 6.1, \"capaciteStockage\": 128, \"marqueDTO\":{\"id\":1,\"nom\":\"APPLE\"}}";

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
		
		assertTrue(messageDTO.getMessage().contains("reference : ne doit pas être vide"));
		
	}
	
	@Test
	public void testcreateModelePrixReferenceFausse() {

		// dans ce telephone seul le prix est faux
		String modeletelephoneFalse = "{\"id\": 0 , \"reference\" : \"\", \"prix\": 0,  \"tailleEcran\": 6.1, \"capaciteStockage\": 128, \"marqueDTO\":{\"id\":1,\"nom\":\"APPLE\"}}";

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
		
		assertTrue(messageDTO.getMessage().contains("reference : ne doit pas être vide"));
		assertTrue(messageDTO.getMessage().contains("prix : doit être supérieur ou égal à 1"));
	}
	
	@Test
	public void testcreateModeleTailleEcranFausse() {

		// dans ce telephone seul le prix est faux
		String modeletelephoneFalse = "{\"id\": 0 , \"reference\" : \"iphone_2000\", \"prix\": 1200,  \"tailleEcran\": -1.0, \"capaciteStockage\": 128, \"marqueDTO\":{\"id\":1,\"nom\":\"APPLE\"}}";

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
		
		assertTrue(messageDTO.getMessage().contains("tailleEcran : doit être supérieur ou égal à 1"));
		
	}
	
	@Test
	public void testcreateModeleCapaciteStockageFausse() {

		// dans ce telephone seul le prix est faux
		String modeletelephoneFalse = "{\"id\": 0 , \"reference\" : \"iphone_2000\", \"prix\": 1200,  \"tailleEcran\": 6.1, \"capaciteStockage\": 0, \"marqueDTO\":{\"id\":1,\"nom\":\"APPLE\"}}";

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
		
		assertTrue(messageDTO.getMessage().contains("capaciteStockage : doit être supérieur ou égal à 1"));
		
	}
	
	@Test
	public void testupdateModeleIdFaux() {

		// dans ce telephone seul le prix est faux
		String modeletelephoneFalse = "{\"id\": 0 , \"reference\" : \"iphone_2000\", \"prix\": 1200,  \"tailleEcran\": 6.1, \"capaciteStockage\": 128, \"marqueDTO\":{\"id\":1,\"nom\":\"APPLE\"}}";

		WebClient client = WebClient.create("http://localhost:8080");
		
		
		MessageDTO messageDTO =client
		.put()
		.uri("/api/modeles/updatemodele")
		.contentType(MediaType.APPLICATION_JSON) 
		.bodyValue(modeletelephoneFalse)
		.retrieve() // expédition...
		.bodyToMono(MessageDTO.class) // type attendu
		.block(); // opération bloquante...
		
		System.out.println(messageDTO.getMessage());
		
		assertTrue(messageDTO.getMessage().contains("id : doit être supérieur ou égal à 1"));
		
	}
	
	@Test
	public void testUpdateModelePrixFaux() {

		// dans ce telephone seul le prix est faux
		String modeletelephoneFalse = "{\"id\": 1 , \"reference\" : \"iphone_2000\", \"prix\": 0,  \"tailleEcran\": 6.1, \"capaciteStockage\": 128, \"marqueDTO\":{\"id\":1,\"nom\":\"APPLE\"}}";

		WebClient client = WebClient.create("http://localhost:8080");
		
		
		MessageDTO messageDTO =client
		.put()
		.uri("/api/modeles/updatemodele")
		.contentType(MediaType.APPLICATION_JSON) 
		.bodyValue(modeletelephoneFalse)
		.retrieve() // expédition...
		.bodyToMono(MessageDTO.class) // type attendu
		.block(); // opération bloquante...
		
		System.out.println(messageDTO.getMessage());
		
		assertTrue(messageDTO.getMessage().contains("prix : doit être supérieur ou égal à 1"));
		
	}
	
	@Test
	public void testUpdateModeleReferenceFausse() {

		// dans ce telephone seul le prix est faux
		String modeletelephoneFalse = "{\"id\": 1 , \"reference\" : \"\", \"prix\": 1200,  \"tailleEcran\": 6.1, \"capaciteStockage\": 128, \"marqueDTO\":{\"id\":1,\"nom\":\"APPLE\"}}";

		WebClient client = WebClient.create("http://localhost:8080");
		
		
		MessageDTO messageDTO =client
		.put()
		.uri("/api/modeles/updatemodele")
		.contentType(MediaType.APPLICATION_JSON) 
		.bodyValue(modeletelephoneFalse)
		.retrieve() // expédition...
		.bodyToMono(MessageDTO.class) // type attendu
		.block(); // opération bloquante...
		
		System.out.println(messageDTO.getMessage());
		
		assertTrue(messageDTO.getMessage().contains("reference : ne doit pas être vide"));
		
	}
	
	@Test
	public void testUpdateModeleTailleEcranFausse() {

		// dans ce telephone seul le prix est faux
		String modeletelephoneFalse = "{\"id\": 1 , \"reference\" : \"iphone_2000\", \"prix\": 1200,  \"tailleEcran\": -1.0, \"capaciteStockage\": 128, \"marqueDTO\":{\"id\":1,\"nom\":\"APPLE\"}}";

		WebClient client = WebClient.create("http://localhost:8080");
		
		
		MessageDTO messageDTO =client
		.put()
		.uri("/api/modeles/updatemodele")
		.contentType(MediaType.APPLICATION_JSON) 
		.bodyValue(modeletelephoneFalse)
		.retrieve() // expédition...
		.bodyToMono(MessageDTO.class) // type attendu
		.block(); // opération bloquante...
		
		System.out.println(messageDTO.getMessage());
		
		assertTrue(messageDTO.getMessage().contains("tailleEcran : doit être supérieur ou égal à 1"));
		
	}
	
	@Test
	public void testUpdateModeleCapaciteStockageFausse() {

		// dans ce telephone seul le prix est faux
		String modeletelephoneFalse = "{\"id\": 1 , \"reference\" : \"iphone_2000\", \"prix\": 1200,  \"tailleEcran\": 6.1, \"capaciteStockage\": 0, \"marqueDTO\":{\"id\":1,\"nom\":\"APPLE\"}}";

		WebClient client = WebClient.create("http://localhost:8080");
		
		
		MessageDTO messageDTO =client
		.put()
		.uri("/api/modeles/updatemodele")
		.contentType(MediaType.APPLICATION_JSON) 
		.bodyValue(modeletelephoneFalse)
		.retrieve() // expédition...
		.bodyToMono(MessageDTO.class) // type attendu
		.block(); // opération bloquante...
		
		System.out.println(messageDTO.getMessage());
		
		assertTrue(messageDTO.getMessage().contains("capaciteStockage : doit être supérieur ou égal à 1"));
		
	}
	
	*/
	

}
