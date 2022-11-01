package com.ninjaone.backendinterviewproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.model.dto.DeviceDto;

@Service
public interface DeviceService {

	DeviceDto save(DeviceDto deviceDto);
	
	List<DeviceDto> get();
	
	DeviceDto update(DeviceDto deviceDto);
	
	List<DeviceDto> getById(Long id);
	
	void delete(Long id);
}
