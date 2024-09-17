package com.example.client.managemante.service.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CustomerNoteDTO {
	
    private Long id;
    private String note;
    private String createdBy;
    private LocalDateTime createdAt;

}
