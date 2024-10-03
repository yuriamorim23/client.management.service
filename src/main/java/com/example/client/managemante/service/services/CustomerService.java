package com.example.client.managemante.service.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.client.managemante.service.dto.ContactDTO;
import com.example.client.managemante.service.dto.CustomerAddressDTO;
import com.example.client.managemante.service.dto.CustomerDTO;
import com.example.client.managemante.service.dto.CustomerSegmentDTO;
import com.example.client.managemante.service.enums.ContactType;
import com.example.client.managemante.service.enums.CustomerStatus;
import com.example.client.managemante.service.enums.CustomerType;
import com.example.client.managemante.service.model.Contact;
import com.example.client.managemante.service.model.Customer;
import com.example.client.managemante.service.model.CustomerAddress;
import com.example.client.managemante.service.model.CustomerSegment;
import com.example.client.managemante.service.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(this::convertToDTO).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Transactional
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDTO(savedCustomer);
    }

    @Transactional
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setDateOfBirth(customerDTO.getDateOfBirth());
        
        try {
            customer.setStatus(CustomerStatus.valueOf(customerDTO.getStatus()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid customer status: " + customerDTO.getStatus());
        }

        try {
            customer.setCustomerType(CustomerType.valueOf(customerDTO.getCustomerType()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid customer type: " + customerDTO.getCustomerType());
        }

        customer.setUpdatedAt(LocalDateTime.now());

        if (customerDTO.getAddresses() != null) {
            List<CustomerAddress> addressList = customerDTO.getAddresses().stream()
                    .map(this::convertDTOToAddress)
                    .collect(Collectors.toList());
            customer.setAddresses(addressList);
        }

        if (customerDTO.getContacts() != null) {
            List<Contact> contactList = customerDTO.getContacts().stream()
                    .map(this::convertDTOToContact)
                    .collect(Collectors.toList());
            customer.setContacts(contactList);
        }

        if (customerDTO.getSegments() != null) {
            Set<CustomerSegment> segmentList = customerDTO.getSegments().stream()
                    .map(this::convertDTOToSegment)
                    .collect(Collectors.toSet());
            customer.setSegments(segmentList);
        }

        Customer updatedCustomer = customerRepository.save(customer);
        return convertToDTO(updatedCustomer);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setDateOfBirth(customer.getDateOfBirth());
        customerDTO.setStatus(customer.getStatus().name());
        customerDTO.setCustomerType(customer.getCustomerType().name());
        customerDTO.setCreatedAt(customer.getCreatedAt());
        customerDTO.setUpdatedAt(customer.getUpdatedAt());

        if (customer.getAddresses() != null) {
            List<CustomerAddressDTO> addressDTOList = customer.getAddresses().stream()
                    .map(this::convertAddressToDTO)
                    .collect(Collectors.toList());
            customerDTO.setAddresses(addressDTOList);
        }

        if (customer.getContacts() != null) {
            List<ContactDTO> contactDTOList = customer.getContacts().stream()
                    .map(this::convertContactToDTO)
                    .collect(Collectors.toList());
            customerDTO.setContacts(contactDTOList);
        }

        if (customer.getSegments() != null) {
            List<CustomerSegmentDTO> segmentDTOList = customer.getSegments().stream()
                    .map(this::convertSegmentToDTO)
                    .collect(Collectors.toList());
            customerDTO.setSegments(segmentDTOList);
        }

        return customerDTO;
    }

    private Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setDateOfBirth(customerDTO.getDateOfBirth());

        customer.setStatus(CustomerStatus.valueOf(customerDTO.getStatus()));
        customer.setCustomerType(CustomerType.valueOf(customerDTO.getCustomerType()));

        customer.setCreatedAt(customerDTO.getCreatedAt());
        customer.setUpdatedAt(customerDTO.getUpdatedAt());

        if (customerDTO.getAddresses() != null) {
            List<CustomerAddress> addressList = customerDTO.getAddresses().stream()
                    .map(this::convertDTOToAddress)
                    .collect(Collectors.toList());
            customer.setAddresses(addressList);
        }

        if (customerDTO.getContacts() != null) {
            List<Contact> contactList = customerDTO.getContacts().stream()
                    .map(this::convertDTOToContact)
                    .collect(Collectors.toList());
            customer.setContacts(contactList);
        }

        if (customerDTO.getSegments() != null) {
            Set<CustomerSegment> segmentList = customerDTO.getSegments().stream()
                    .map(this::convertDTOToSegment)
                    .collect(Collectors.toSet());
            customer.setSegments(segmentList);
        }

        return customer;
    }

    private CustomerAddress convertDTOToAddress(CustomerAddressDTO addressDTO) {
        CustomerAddress address = new CustomerAddress();
        address.setId(addressDTO.getId());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setCountry(addressDTO.getCountry());
        return address;
    }

    private Contact convertDTOToContact(ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setId(contactDTO.getId());
        contact.setType(ContactType.valueOf(contactDTO.getType()));
        contact.setValue(contactDTO.getValue());
        return contact;
    }

    private CustomerSegment convertDTOToSegment(CustomerSegmentDTO segmentDTO) {
        CustomerSegment segment = new CustomerSegment();
        segment.setId(segmentDTO.getId());
        segment.setName(segmentDTO.getName());
        segment.setDescription(segmentDTO.getDescription());
        return segment;
    }
    
    private CustomerAddressDTO convertAddressToDTO(CustomerAddress address) {
        CustomerAddressDTO addressDTO = new CustomerAddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setPostalCode(address.getPostalCode());
        addressDTO.setCountry(address.getCountry());
        return addressDTO;
    }

    private ContactDTO convertContactToDTO(Contact contact) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(contact.getId());
        contactDTO.setType(contact.getType().name());
        contactDTO.setValue(contact.getValue());
        return contactDTO;
    }

    private CustomerSegmentDTO convertSegmentToDTO(CustomerSegment segment) {
        CustomerSegmentDTO segmentDTO = new CustomerSegmentDTO();
        segmentDTO.setId(segment.getId());
        segmentDTO.setName(segment.getName());
        segmentDTO.setDescription(segment.getDescription());
        return segmentDTO;
    }

}
