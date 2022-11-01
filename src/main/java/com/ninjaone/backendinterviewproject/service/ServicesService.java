package com.ninjaone.backendinterviewproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.model.dto.ServicesCostsDto;
import com.ninjaone.backendinterviewproject.model.dto.ServicesDto;

@Service
public interface ServicesService {

	ServicesDto save(ServicesDto servicesDto);
	
	List<ServicesDto> get();
	
	List<ServicesDto> getById(Long id);
	
	void delete(Long id);
	
	ServicesCostsDto getMonthlyCosts(ServicesDto servicesDto);
}
