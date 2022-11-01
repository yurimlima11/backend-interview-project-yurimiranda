package com.backend.interview.project.yurimiranda.tests.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.backendinterviewproject.Application;
import com.ninjaone.backendinterviewproject.controller.ServicesController;
import com.ninjaone.backendinterviewproject.enums.DeviceTypeEnum;
import com.ninjaone.backendinterviewproject.enums.ServiceTypeEnum;
import com.ninjaone.backendinterviewproject.model.dto.DeviceDto;
import com.ninjaone.backendinterviewproject.model.dto.DeviceTypeDto;
import com.ninjaone.backendinterviewproject.model.dto.ServicesCostsDto;
import com.ninjaone.backendinterviewproject.model.dto.ServicesDto;
import com.ninjaone.backendinterviewproject.model.dto.ServicesTypeDto;
import com.ninjaone.backendinterviewproject.service.ServicesService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Application.class})
@WebMvcTest(ServicesController.class)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class ServicesControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	private ServicesService servicesService;
	
	private ServicesDto servicesDto;
	
	private ServicesTypeDto servicesTypeDto;
	
	private DeviceDto deviceDto;
	
	private DeviceTypeDto deviceTypeDto;
	
	private ServicesCostsDto servicesCostsDto; 
	
	private List<ServicesDto> listServicesDto = new ArrayList<ServicesDto>();
	
	@BeforeEach
	void setUp() {
		deviceTypeDto = new DeviceTypeDto().builder().id(Long.valueOf(1)).deviceTypeName(DeviceTypeEnum.WINDOWS_SERVER.getDescription()).build();
		deviceDto = new DeviceDto().builder().id(Long.valueOf(1)).systemName("System-Mock").deviceTypeDto(deviceTypeDto).build();
		servicesTypeDto = new ServicesTypeDto().builder().id(Long.valueOf(1)).serviceName(ServiceTypeEnum.ANTIVIRUS.getDescription()).costs(5.0).build();
		servicesDto = new ServicesDto().builder().id(Long.valueOf(1)).deviceDto(deviceDto).serviceTypeDto(servicesTypeDto).build();
		servicesCostsDto = new ServicesCostsDto().builder().antivirusCost(5).backupCost(0).devicesCost(0).psaCost(0).screenShareCost(0).build();
		listServicesDto.add(servicesDto);
	}
	
	@Test
	void getServices() throws Exception{
		when(servicesService.get()).thenReturn(listServicesDto);
		
        mockMvc.perform(get("/services"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().string(objectMapper.writeValueAsString(listServicesDto)));
	}
	
	@Test
	void getServicesById() throws Exception{
		when(servicesService.getById(servicesDto.getId())).thenReturn(listServicesDto);
		
        mockMvc.perform(get("/services/" + servicesDto.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().string(objectMapper.writeValueAsString(listServicesDto)));
	}
	
	@Test
	void createServices() throws Exception {
        when(servicesService.save(any())).thenReturn(servicesDto);

        mockMvc.perform(post("/services")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(servicesDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(servicesDto)));
	}
	
	@Test
	void getMonthlyCosts() throws Exception {
        when(servicesService.getMonthlyCosts(any())).thenReturn(servicesCostsDto);

        mockMvc.perform(get("/services/monthly-costs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(servicesCostsDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(servicesCostsDto)));
	}
	
	@Test
    void deleteServices() throws Exception {
        doNothing().when(servicesService).delete(servicesDto.getId());

        mockMvc.perform(delete("/services/" + servicesDto.getId()))
                .andExpect(status().isOk());
    }
}
