package com.application.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.model.Driver;
import com.application.repo.DriversRepo;
@Service
public class DriverServiceImpl implements DriverService{
	
	@Autowired
	private DriversRepo driverRepo;
	
	@Override
	public Driver registerDriver(Driver d) {
		return driverRepo.save(d);
	}

}
