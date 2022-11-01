package com.ninjaone.backendinterviewproject.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.backendinterviewproject.model.dto.ServicesDto;
import com.ninjaone.backendinterviewproject.service.ServicesService;

@RestController
@RequestMapping("/services")
public class ServicesController {

	@Autowired
	private ServicesService servicesService;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody ServicesDto servicesDto){
		return ResponseEntity.ok(servicesService.save(servicesDto));
	}
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(servicesService.get());
	}
	
	@GetMapping("/monthly-costs")
	public ResponseEntity<?> getMonthlyCosts(@RequestBody ServicesDto servicesDto){
		return ResponseEntity.ok(servicesService.getMonthlyCosts(servicesDto));
	}
	
    @GetMapping("/{id}")
    private ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(servicesService.getById(id));
    }
	
    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id){
    	servicesService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
	
	
}
