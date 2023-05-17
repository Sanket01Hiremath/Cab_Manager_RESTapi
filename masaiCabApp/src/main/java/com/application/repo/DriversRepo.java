package com.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.model.Driver;

public interface DriversRepo extends JpaRepository<Driver, Integer>{

}
