package com.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.Service.DriverService;
import com.application.Service.UserService;
import com.application.model.Driver;
import com.application.model.Role;
import com.application.model.Users;

@RestController
@RequestMapping("/masaiCab")
public class UsersController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private DriverService driverService;
	@Autowired
	private PasswordEncoder encoder;
	
	
	@PostMapping("/user/register")
	public ResponseEntity<Users> registerUser(@RequestBody Users u){
		u.setPassword(encoder.encode(u.getPassword()));
		Users saved=userService.registerUser(u);
		return new ResponseEntity<>(saved,HttpStatus.CREATED);
	}
	
	@PostMapping("/user/register")
	public ResponseEntity<Role> addRole(@RequestBody Role r){
		Role saved=userService.addRole(r);
		return new ResponseEntity<>(saved,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/findride")
	public ResponseEntity<List<Driver>> findRides() throws Exception{
		List<Driver> saved=userService.findRides();
		return new ResponseEntity<>(saved,HttpStatus.OK);
	}
	
	@PutMapping("/user/book/{driverId}/{x}/{y}")
	public ResponseEntity<Users> BookRide(@PathVariable Integer driverId,Integer x,Integer y) throws Exception{
		Users saved=userService.BookRide(driverId, x, y);
		return new ResponseEntity<>(saved,HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<Users>> getUsers() throws Exception{
		List<Users> saved=userService.getUsers();
		return new ResponseEntity<>(saved,HttpStatus.OK);
	}
	
	@PutMapping("/user/{userId}")
	public ResponseEntity<Users> updateUsers(@RequestBody Users u,@PathVariable Integer userId){
		Users saved=userService.updateUsers(u,userId);
		return new ResponseEntity<>(saved,HttpStatus.OK);
	}
}
