package com.example.client.managemante.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.client.managemante.service.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}

