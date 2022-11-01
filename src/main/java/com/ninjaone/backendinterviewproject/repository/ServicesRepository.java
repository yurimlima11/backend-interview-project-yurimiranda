package com.ninjaone.backendinterviewproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.Services;
import com.ninjaone.backendinterviewproject.model.ServicesType;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long>{
	List<Services> findByServicesTypeAndDevice(ServicesType servicesType, Device devices);
	List<Services> findByDeviceIn(List<Device> devices);

}
