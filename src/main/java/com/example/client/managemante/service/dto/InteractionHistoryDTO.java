package com.example.client.managemante.service.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class InteractionHistoryDTO {
	
    private Long id;
    private String interactionType;
    private LocalDateTime interactionDate;
    private String notes;
    private String createdBy;

}

