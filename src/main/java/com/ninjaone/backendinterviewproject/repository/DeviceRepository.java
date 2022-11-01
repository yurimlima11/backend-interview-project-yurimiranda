package com.ninjaone.backendinterviewproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.DeviceType;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

	List<Device> findBySystemNameAndDeviceType(String systemName, DeviceType deviceType);
	List<Device> findBySystemName(String systemName);
}
