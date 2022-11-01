package com.ninjaone.backendinterviewproject.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.dto.converter.DeviceConverter;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.dto.DeviceDto;
import com.ninjaone.backendinterviewproject.repository.DeviceRepository;
import com.ninjaone.backendinterviewproject.service.DeviceService;

@Service
public class DeviceServiceImpl implements DeviceService{

	@Autowired
	private DeviceConverter deviceConverter;
	
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Override
	public DeviceDto save(DeviceDto deviceDto) {
		Device device = deviceConverter.extractedToNewEntity(deviceDto);
		List<Device> listDevice = deviceRepository.findBySystemNameAndDeviceType(device.getSystemName(), device.getDeviceType());
		if(!listDevice.isEmpty()) {
			return null;
		}
		device = deviceRepository.save(device);
		return deviceConverter.extractedToDto(device);
	}

	@Override
	public List<DeviceDto> get() {
		List<DeviceDto> listDeviceDto = new ArrayList<DeviceDto>();
		listDeviceDto = deviceConverter.extractedToListDto(deviceRepository.findAll());
		return listDeviceDto;
	}
	
	@Override
	public List<DeviceDto> getById(Long id) {
		List<DeviceDto> listDeviceDto = new ArrayList<DeviceDto>();
		listDeviceDto = deviceConverter.extractedToListDto(deviceRepository.findById(id).stream().collect(Collectors.toList()));
		return listDeviceDto;
	}

	@Override
	public DeviceDto update(DeviceDto deviceDto) {
		Device deviceUpdate = new Device();
		Optional<Device> device = deviceRepository.findById(deviceDto.getId());
		if(device.isPresent()) {
			deviceUpdate = deviceConverter.extractedToEntity(deviceDto);
			deviceUpdate = deviceRepository.save(deviceUpdate);
			return deviceConverter.extractedToDto(deviceUpdate);
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		Optional<Device> device = deviceRepository.findById(id);
		if(device.isPresent()) {
			deviceRepository.deleteById(device.get().getId());
		}
	}

}
