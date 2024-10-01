package com.example.client.managemante.service.impl;

import java.util.List;

import com.example.client.managemante.service.dto.ContactDTO;

public interface ContactServiceImpl {
	
	 List<ContactDTO> getContactsByCustomerId(Long customerId);
	 ContactDTO addContact(ContactDTO contactDTO);
	 void deleteContact(Long id);	

}
