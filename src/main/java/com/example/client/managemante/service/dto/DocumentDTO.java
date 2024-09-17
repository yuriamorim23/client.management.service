package com.example.client.managemante.service.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DocumentDTO {
	
    private Long id;
    private String documentType;
    private String documentPath;
    private LocalDateTime uploadedAt;

}

