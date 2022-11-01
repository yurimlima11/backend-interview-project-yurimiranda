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
public class ServicesDto {

	private Long id;
	private DeviceDto deviceDto;
	private ServicesTypeDto serviceTypeDto;
}
