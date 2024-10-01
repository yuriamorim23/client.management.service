package com.example.client.managemante.service.impl;

import java.util.List;

import com.example.client.managemante.service.dto.InteractionHistoryDTO;

public interface InteractionHistoryServiceImpl {
	
	List<InteractionHistoryDTO> getInteractionsByCustomerId(Long customerId);
    InteractionHistoryDTO addInteraction(InteractionHistoryDTO interactionHistoryDTO);
    void deleteInteraction(Long id);

}
