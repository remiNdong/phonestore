package com.phonestore.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	
	void store(MultipartFile file, String nomFichier);
	
	void delete(String nomFichier);

}
