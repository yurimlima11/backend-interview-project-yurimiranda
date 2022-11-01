package com.ninjaone.backendinterviewproject.service.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.dto.converter.DeviceConverter;
import com.ninjaone.backendinterviewproject.dto.converter.ServicesConverter;
import com.ninjaone.backendinterviewproject.enums.DeviceTypeEnum;
import com.ninjaone.backendinterviewproject.enums.ServiceTypeEnum;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.Services;
import com.ninjaone.backendinterviewproject.model.dto.ServicesCostsDto;
import com.ninjaone.backendinterviewproject.model.dto.ServicesDto;
import com.ninjaone.backendinterviewproject.repository.DeviceRepository;
import com.ninjaone.backendinterviewproject.repository.ServicesRepository;
import com.ninjaone.backendinterviewproject.service.ServicesService;

@Service
public class ServicesServiceImpl implements ServicesService {

	@Autowired
	private ServicesConverter servicesConverter;
	
	@Autowired
	private ServicesRepository servicesRepository;
	
	@Autowired
	private DeviceConverter deviceConverter;
	
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Override
	public ServicesDto save(ServicesDto servicesDto) {
		Services services = servicesConverter.extractedToEntity(servicesDto);
		List<Services> listServices = servicesRepository.findByServicesTypeAndDevice(services.getServicesType(), services.getDevice());
		if(!listServices.isEmpty()) {
			return null;
		}
		services = servicesRepository.save(services);
		return servicesConverter.extractedToDto(services);
	}

	@Override
	public List<ServicesDto> get() {
		List<ServicesDto> listServicesDto = new ArrayList<ServicesDto>();
		listServicesDto = servicesConverter.extractedToListDto(servicesRepository.findAll());
		return listServicesDto;
	}
	
	@Override
	public List<ServicesDto> getById(Long id) {
		List<ServicesDto> listServicesDto = new ArrayList<ServicesDto>();
		listServicesDto = servicesConverter.extractedToListDto(servicesRepository.findById(id).stream().collect(Collectors.toList()));
		return listServicesDto;
	}

	@Override
	public void delete(Long id) {
		Optional<Services> listServices = servicesRepository.findById(id);
		if(listServices.isPresent()) {
			servicesRepository.deleteById(listServices.get().getId());
		}
	}

	@Override
	public ServicesCostsDto getMonthlyCosts(ServicesDto servicesDto) {
		List<Device> listDevice = deviceRepository.findBySystemName(servicesDto.getDeviceDto().getSystemName());
		List<Services> listServices = servicesRepository.findByDeviceIn(listDevice);
		return calculate(listServices);
	}
	
	private ServicesCostsDto calculate(List<Services> listServices){
		
		List<Services> listAntivirus = listServices.stream()
				.filter(service -> service.getServicesType().getServiceTypeName().equals(ServiceTypeEnum.ANTIVIRUS.getDescription()))
				.collect(Collectors.toList());
		
		List<Services> listBackup = listServices.stream()
				.filter(service -> service.getServicesType().getServiceTypeName().equals(ServiceTypeEnum.BACKUP.getDescription()))
				.collect(Collectors.toList());
		
		List<Services> listScreenShare = listServices.stream()
				.filter(service -> service.getServicesType().getServiceTypeName().equals(ServiceTypeEnum.SCREEN_SHARE.getDescription()))
				.collect(Collectors.toList());;
		
		List<Services> listPSA = listServices.stream()
				.filter(service -> service.getServicesType().getServiceTypeName().equals(ServiceTypeEnum.PSA.getDescription()))
				.collect(Collectors.toList());;
		
		List<Services> listAntivirusWindows = listAntivirus.stream().
				filter(service -> service.getDevice().getDeviceType().getDeviceTypeName().equals(DeviceTypeEnum.WINDOWS_SERVER.getDescription()) ||
				service.getDevice().getDeviceType().getDeviceTypeName().equals(DeviceTypeEnum.WINDOWS_WORKSTATION.getDescription()))
		.collect(Collectors.toList());
		
		List<Services> listAntivirusMac = listAntivirus.stream().
				filter(service -> service.getDevice().getDeviceType().getDeviceTypeName().equals(DeviceTypeEnum.MAC.getDescription()))
		.collect(Collectors.toList());
		
		
		
		return doCalculate(listAntivirusWindows, listAntivirusMac, listBackup, listScreenShare, listPSA);
				
	}
	
	private ServicesCostsDto doCalculate(List<Services> listAntivirusWindows, List<Services> listAntivirusMac, List<Services> listBackup, List<Services> listScreenShare, 
			List<Services> listPSA){

		return servicesConverter.doCalculate(listAntivirusWindows, listAntivirusMac, listBackup, listScreenShare, listPSA);
	}
}
