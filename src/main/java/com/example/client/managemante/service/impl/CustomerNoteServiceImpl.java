package com.example.client.managemante.service.impl;

import java.util.List;

import com.example.client.managemante.service.dto.CustomerNoteDTO;

public interface CustomerNoteServiceImpl {

	List<CustomerNoteDTO> getNotesByCustomerId(Long customerId);
    CustomerNoteDTO addNote(CustomerNoteDTO customerNoteDTO);
    void deleteNote(Long id);
}
