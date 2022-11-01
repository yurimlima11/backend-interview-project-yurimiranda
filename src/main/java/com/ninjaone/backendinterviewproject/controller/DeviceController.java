package com.ninjaone.backendinterviewproject.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.backendinterviewproject.model.dto.DeviceDto;
import com.ninjaone.backendinterviewproject.service.DeviceService;

@RestController
@RequestMapping("/device")
public class DeviceController {

	@Autowired
	private DeviceService deviceService;
	
	@GetMapping
	public ResponseEntity<?> getAllDevices(){
		return ResponseEntity.ok(deviceService.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody DeviceDto deviceDto){
		return ResponseEntity.ok(deviceService.save(deviceDto));
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody DeviceDto deviceDto){
		return ResponseEntity.ok(deviceService.update(deviceDto));
	}
	
    @GetMapping("/{id}")
    private ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(deviceService.getById(id));
    }
	
    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id){
    	deviceService.delete(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
	
    
}
