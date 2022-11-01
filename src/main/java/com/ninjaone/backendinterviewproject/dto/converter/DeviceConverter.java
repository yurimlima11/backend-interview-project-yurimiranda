package com.ninjaone.backendinterviewproject.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.dto.DeviceDto;

@Component
public class DeviceConverter {

	@Autowired
	private DeviceTypeConverter deviceTypeConverter;
	
	public DeviceDto extractedToDto(Device device) {
		return new DeviceDto().builder()
				.id(device.getId())
				.systemName(device.getSystemName())
				.deviceTypeDto(deviceTypeConverter.extractedToDto(device.getDeviceType()))
				.build();
	}
	
	public List<DeviceDto> extractedToListDto(List<Device> listDevice){
		List<DeviceDto> listDeviceDto = new ArrayList<DeviceDto>();
		for (Device device : listDevice) {
			listDeviceDto.add(extractedToDto(device));
		}
		return listDeviceDto;
	}
	
	public Device extractedToEntity(DeviceDto deviceDto) {
		return new Device().builder()
				.id(deviceDto.getId())
				.systemName(deviceDto.getSystemName())
				.deviceType(deviceTypeConverter.extractedToEntity(deviceDto.getDeviceTypeDto()))
				.build();
	}
	
	public List<Device> extractedToListEntity(List<DeviceDto> listDeviceDto){
		List<Device> listDevice = new ArrayList<Device>();
		for (DeviceDto deviceDto : listDeviceDto) {
			listDevice.add(extractedToEntity(deviceDto));
		}
		return listDevice;
	}
	
	public Device extractedToNewEntity(DeviceDto deviceDto) {
		return new Device().builder()
				.id(null)
				.systemName(deviceDto.getSystemName())
				.deviceType(deviceTypeConverter.extractedToEntity(deviceDto.getDeviceTypeDto()))
				.build();
	}
	
}
