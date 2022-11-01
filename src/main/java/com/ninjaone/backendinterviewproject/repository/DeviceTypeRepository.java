package com.ninjaone.backendinterviewproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.DeviceType;

@Repository
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Long>{

}
