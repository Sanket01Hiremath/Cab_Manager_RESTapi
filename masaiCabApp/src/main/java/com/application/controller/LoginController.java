package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.Users;
import com.application.repo.UsersRepo;

@RestController
public class LoginController {
	@Autowired
	private UsersRepo userRepo;
	
	@GetMapping("/masaiCab/user/login")
	public ResponseEntity<Users> getLoggedInCustomerDetailsHandler(Authentication auth){
		System.out.println(auth);
		 Users user= userRepo.findUsersByPhone(auth.getName()).orElseThrow(() -> new BadCredentialsException("Invalid Username or password"));
		 //to get the token in body, pass HttpServletResponse inside this method parameter 
		 //System.out.println(response.getHeaders(SecurityConstants.JWT_HEADER));
		 return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
		
		
	}
}
