package com.ninjaone.backendinterviewproject.model.dto;

import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {

	private Long id;
	private String systemName;
	private DeviceTypeDto deviceTypeDto;
}
