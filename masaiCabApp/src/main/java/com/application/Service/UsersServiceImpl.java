package com.application.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.application.model.Driver;
import com.application.model.Role;
import com.application.model.Users;
import com.application.repo.DriversRepo;
import com.application.repo.UsersRepo;

@Service
public class UsersServiceImpl implements UserService{

	@Autowired
	private UsersRepo userRepo;
	@Autowired
	private DriversRepo driverRepo;
	
	Authentication auth;
	
	@Override
	public Users registerUser(Users u) {
		return userRepo.save(u);
	}

	@Override
	public List<Driver> findRides(){
		Users found= userRepo.findUsersByPhone(auth.getName()).orElseThrow(() -> new BadCredentialsException("Invalid Username or password"));
		int x1=found.getPosition()[0];
		int y1=found.getPosition()[1];
		List<Driver> list=driverRepo.findAll();
		List<Driver> list1=new ArrayList<>();
		for(Driver d:list) {
			int x2=d.getPosition()[0];
			int y2=d.getPosition()[1];
			double ans= Math.sqrt(Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2));
			if(ans<=5) {
				list1.add(d);
			}
		}
		return list1;
	}

	@Override
	public Users BookRide(Integer did, Integer x, Integer y) {
		Users user= userRepo.findUsersByPhone(auth.getName()).orElseThrow(() -> new BadCredentialsException("Invalid Username or password"));
		Driver d=driverRepo.findById(did).get();
		Integer[] arr= {x,y};
		d.setPosition(arr);
		driverRepo.save(d);
		user.setPosition(arr);
		return userRepo.save(user);
	}

	@Override
	public Users updateUsers(Users u, Integer id) {
		Users user= userRepo.findUsersByPhone(auth.getName()).orElseThrow(() -> new BadCredentialsException("Invalid Username or password"));
		user.setPosition(u.getPosition());
		user.setFirstName(u.getFirstName());
		user.setLastName(u.getLastName());
		return userRepo.save(user);
	}

	@Override
	public List<Users> getUsers() {
		Users user= userRepo.findUsersByPhone(auth.getName()).orElseThrow(() -> new BadCredentialsException("Invalid Username or password"));
		List<Role> list=user.getRoles();
		for(Role r:list) {
			if(r.getName()=="Admin") {
				return userRepo.findAll();
			}
		}
		List<Users> temp= new ArrayList<>();
		temp.add(user);
		return temp;
	}

	@Override
	public Role addRole(Role r) {
		Users user= userRepo.findUsersByPhone(auth.getName()).orElseThrow(() -> new BadCredentialsException("Invalid Username or password"));
		user.getRoles().add(r);
		r.setUser(user);
		userRepo.save(user);
		return r;
	}
	
}
