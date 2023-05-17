package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.Service.DriverService;
import com.application.model.Driver;
import com.application.model.Role;

@RestController
@RequestMapping("/masaiCab/driver")
public class DriversController {
	
	@Autowired
	private DriverService driverService;
	
	@PostMapping("/register")
	public ResponseEntity<Driver> addRole(@RequestBody Driver d){
		Driver saved=driverService.registerDriver(d);
		return new ResponseEntity<>(saved,HttpStatus.CREATED);
	}

}
