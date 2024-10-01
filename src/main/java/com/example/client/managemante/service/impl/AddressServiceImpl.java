package com.example.client.managemante.service.impl;

import java.util.List;

import com.example.client.managemante.service.dto.CustomerAddressDTO;

public interface AddressServiceImpl {
	
    List<CustomerAddressDTO> getAddressesByCustomerId(Long customerId);
    CustomerAddressDTO addAddress(CustomerAddressDTO addressDTO);
    void deleteAddress(Long id);
}
