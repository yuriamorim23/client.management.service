package com.example.client.managemante.service.dto;

import lombok.Data;

@Data
public class CustomerAddressDTO {
	
    private Long id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    
}