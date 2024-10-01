package com.example.client.managemante.service.impl;

import java.util.List;

import com.example.client.managemante.service.dto.CustomerPreferencesDTO;

public interface CustomerPreferencesServiceImpl {
	
	List<CustomerPreferencesDTO> getPreferencesByCustomerId(Long customerId);
    CustomerPreferencesDTO addPreference(CustomerPreferencesDTO customerPreferencesDTO);
    void deletePreference(Long id);

}
