package com.example.client.managemante.service.dto;

import lombok.Data;

@Data
public class CustomerPreferencesDTO {
	
    private Long id;
    private String preferenceType;
    private String preferenceValue;

}

