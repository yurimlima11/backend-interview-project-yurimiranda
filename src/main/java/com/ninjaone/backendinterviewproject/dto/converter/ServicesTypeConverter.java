package com.ninjaone.backendinterviewproject.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ninjaone.backendinterviewproject.model.ServicesType;
import com.ninjaone.backendinterviewproject.model.dto.ServicesTypeDto;

@Component
public class ServicesTypeConverter {

	public ServicesTypeDto extractedToDto(ServicesType servicesType) {
		return new ServicesTypeDto().builder()
				.id(servicesType.getId())
				.costs(servicesType.getCosts())
				.serviceName(servicesType.getServiceTypeName())
				.build();
	}
	
	public List<ServicesTypeDto> extractedToListDto(List<ServicesType> listServicesType){
		List<ServicesTypeDto> listServicesTypeDto = new ArrayList<ServicesTypeDto>();
		for (ServicesType servicesType : listServicesType) {
			listServicesTypeDto.add(extractedToDto(servicesType));
		}
		return listServicesTypeDto;
	}
	
	public ServicesType extractedToEntity(ServicesTypeDto servicesTypeDto) {
		return new ServicesType().builder()
				.id(servicesTypeDto.getId())
				.costs(servicesTypeDto.getCosts())
				.serviceTypeName(servicesTypeDto.getServiceName())
				.build();
	}
	
	public List<ServicesType> extractedToListEntity(List<ServicesTypeDto> listServicesTypeDto){
		List<ServicesType> listServicesType = new ArrayList<ServicesType>();
		for (ServicesTypeDto servicesTypeDto : listServicesTypeDto) {
			listServicesType.add(extractedToEntity(servicesTypeDto));
		}
		return listServicesType;
	}
	
}
