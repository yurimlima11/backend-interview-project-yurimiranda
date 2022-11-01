package com.ninjaone.backendinterviewproject.dto.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ninjaone.backendinterviewproject.enums.DeviceTypeEnum;
import com.ninjaone.backendinterviewproject.model.Services;
import com.ninjaone.backendinterviewproject.model.dto.ServicesCostsDto;
import com.ninjaone.backendinterviewproject.model.dto.ServicesDto;

@Component
public class ServicesConverter {

	@Autowired
	private DeviceConverter deviceConverter;
	
	@Autowired
	private ServicesTypeConverter servicesTypeConverter;
	
	@Autowired
	private ServicesConverter servicesConverter;
	
	public ServicesDto extractedToDto(Services services) {
		return new ServicesDto().builder()
				.id(services.getId())
				.deviceDto(deviceConverter.extractedToDto(services.getDevice()))
				.serviceTypeDto(servicesTypeConverter.extractedToDto(services.getServicesType()))
				.build();
	}
	
	public List<ServicesDto> extractedToListDto(List<Services> listServices){
		List<ServicesDto> listServicesDto = new ArrayList<ServicesDto>();
		for (Services services : listServices) {
			listServicesDto.add(extractedToDto(services));
		}
		return listServicesDto;
	}
	
	public Services extractedToEntity(ServicesDto servicesDto) {
		return new Services().builder()
				.id(servicesDto.getId())
				.device(deviceConverter.extractedToEntity(servicesDto.getDeviceDto()))
				.servicesType(servicesTypeConverter.extractedToEntity(servicesDto.getServiceTypeDto()))
				.build();
	}
	
	public Services extractedToNewEntity(ServicesDto servicesDto) {
		return new Services().builder()
				.id(null)
				.device(deviceConverter.extractedToEntity(servicesDto.getDeviceDto()))
				.servicesType(servicesTypeConverter.extractedToEntity(servicesDto.getServiceTypeDto()))
				.build();
	}

	public ServicesCostsDto doCalculate(List<Services> listAntivirusWindows, List<Services> listAntivirusMac, List<Services> listBackup, List<Services> listScreenShare, 
			List<Services> listPSA){
		Integer totalValue;
		Integer deviceValue;
		Integer antivirusValueMac;
		Integer antivirusValueWindows;
		Integer antivirusTotalValue;
		Integer backupValue;
		Integer screenShareValue;
		Integer psaValue;
		
		deviceValue = 4 * (listAntivirusWindows.size() + listAntivirusMac.size() + listBackup.size() + listPSA.size() + listScreenShare.size());
		backupValue = 3 * listBackup.size();
		screenShareValue = 1 * listScreenShare.size();
		
		antivirusValueMac = 7 * listAntivirusMac.size();
		
		antivirusValueWindows = 5 * listAntivirusWindows.size();
		
		psaValue = 2 * listPSA.size();
		
		antivirusTotalValue = antivirusValueMac + antivirusValueWindows;
		
		totalValue = deviceValue + antivirusTotalValue + backupValue + screenShareValue + psaValue;
		
		return extractedCalculation(totalValue, deviceValue, antivirusTotalValue, backupValue, screenShareValue, psaValue);
		
	}
	
	private ServicesCostsDto extractedCalculation(Integer totalValue, Integer deviceValue, Integer antivirusTotalValue, Integer backupValue, Integer screenShareValue,  Integer psaValue) {
		return new ServicesCostsDto().builder()
				.totalValueService(totalValue)
				.devicesCost(deviceValue)
				.antivirusCost(antivirusTotalValue)
				.backupCost(backupValue)
				.screenShareCost(screenShareValue)
				.psaCost(psaValue)
				.build();
	}
}
