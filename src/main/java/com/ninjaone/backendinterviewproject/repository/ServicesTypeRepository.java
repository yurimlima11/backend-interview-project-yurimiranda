package com.ninjaone.backendinterviewproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.ServicesType;

@Repository
public interface ServicesTypeRepository extends JpaRepository<ServicesType, Long>{

}
