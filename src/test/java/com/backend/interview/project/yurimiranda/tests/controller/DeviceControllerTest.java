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
import com.ninjaone.backendinterviewproject.controller.DeviceController;
import com.ninjaone.backendinterviewproject.dto.converter.DeviceConverter;
import com.ninjaone.backendinterviewproject.enums.DeviceTypeEnum;
import com.ninjaone.backendinterviewproject.model.dto.DeviceDto;
import com.ninjaone.backendinterviewproject.model.dto.DeviceTypeDto;
import com.ninjaone.backendinterviewproject.service.DeviceService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Application.class})
@WebMvcTest(DeviceController.class)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class DeviceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	private DeviceService deviceService;
	
	@MockBean
	private DeviceConverter deviceConverter;
	
	private DeviceDto deviceDto;
	
	private DeviceTypeDto deviceTypeDto;
	
	private List<DeviceDto> listDeviceDto = new ArrayList<DeviceDto>();
	
	@BeforeEach
	void setUp() {
		deviceTypeDto = new DeviceTypeDto().builder().id(Long.valueOf(3)).deviceTypeName(DeviceTypeEnum.MAC.getDescription()).build();
		deviceDto = new DeviceDto().builder().id(Long.valueOf(6)).systemName("System-Mock").deviceTypeDto(deviceTypeDto).build();
		listDeviceDto.add(deviceDto);
	}
	
	@Test
	void getDevice() throws Exception{
		when(deviceService.get()).thenReturn(listDeviceDto);
		
        mockMvc.perform(get("/device"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().string(objectMapper.writeValueAsString(listDeviceDto)));
	}
	
	@Test
	void getDeviceById() throws Exception{
		when(deviceService.getById(deviceDto.getId())).thenReturn(listDeviceDto);
		
        mockMvc.perform(get("/device/" + deviceDto.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().string(objectMapper.writeValueAsString(listDeviceDto)));
	}
	 
	@Test
	void createDevice() throws Exception {
        when(deviceService.save(any())).thenReturn(deviceDto);

        mockMvc.perform(post("/device")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deviceDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(deviceDto)));
	}
	
	@Test
	void updateDevice() throws Exception {
        when(deviceService.update(any())).thenReturn(deviceDto);

        mockMvc.perform(put("/device")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deviceDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(deviceDto)));
	}
	
	@Test
    void deleteDevice() throws Exception {
        doNothing().when(deviceService).delete(deviceDto.getId());

        mockMvc.perform(delete("/device/" + deviceDto.getId()))
                .andExpect(status().isOk());
    }
}
