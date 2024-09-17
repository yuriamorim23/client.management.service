package com.example.client.managemante.service.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;


@Data
public class CustomerDTO {
	
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDateTime dateOfBirth;
    private String status;
    private String customerType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CustomerAddressDTO> addresses;
    private List<ContactDTO> contacts;
    private List<CustomerSegmentDTO> segments;

}
