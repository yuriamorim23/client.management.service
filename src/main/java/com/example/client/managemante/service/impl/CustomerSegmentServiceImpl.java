package com.example.client.managemante.service.impl;

import java.util.List;

import com.example.client.managemante.service.dto.CustomerSegmentDTO;

public interface CustomerSegmentServiceImpl {
	
	List<CustomerSegmentDTO> getAllSegments();
    CustomerSegmentDTO getSegmentById(Long id);
    CustomerSegmentDTO createSegment(CustomerSegmentDTO segmentDTO);
    void deleteSegment(Long id);
}
