package com.example.client.managemante.service.controller.impl;

import java.util.List;

import com.example.client.managemante.service.dto.CustomerDTO;

public interface CustomerControllerImpl {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomer(Long id);
}