package com.example.client.managemante.service.impl;

import java.util.List;

import com.example.client.managemante.service.dto.DocumentDTO;

public interface DocumentServiceImpl {
	
	List<DocumentDTO> getDocumentsByCustomerId(Long customerId);
    DocumentDTO addDocument(DocumentDTO documentDTO);
    void deleteDocument(Long id);

}
