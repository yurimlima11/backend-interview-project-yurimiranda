package com.ninjaone.backendinterviewproject.dto.converter;

import org.springframework.stereotype.Component;

import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.model.dto.DeviceTypeDto;

@Component
public class DeviceTypeConverter {
	
	public DeviceTypeDto extractedToDto(DeviceType deviceType) {
		return new DeviceTypeDto().builder()
				.id(deviceType.getId())
				.deviceTypeName(deviceType.getDeviceTypeName())
				.build();
	}
	
	public DeviceType extractedToEntity(DeviceTypeDto deviceTypeDto) {
		return new DeviceType().builder()
				.id(deviceTypeDto.getId())
				.deviceTypeName(deviceTypeDto.getDeviceTypeName())
				.build();
	}
}
