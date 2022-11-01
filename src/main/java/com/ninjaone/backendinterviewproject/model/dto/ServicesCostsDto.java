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
public class ServicesCostsDto {
	
	private Integer devicesCost;
	
	private Integer antivirusCost;
	
	private Integer backupCost;
	
	private Integer screenShareCost;
	
	private Integer psaCost;
	
	private Integer totalValueService;
	
}
