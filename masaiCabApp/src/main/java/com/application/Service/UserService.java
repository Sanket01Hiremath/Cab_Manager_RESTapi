package com.application.Service;

import java.util.List;

import com.application.model.Driver;
import com.application.model.Role;
import com.application.model.Users;

public interface UserService {
	public Users registerUser(Users u);
	public List<Driver> findRides();
	public Users BookRide(Integer did,Integer x,Integer y);
	public Users updateUsers(Users u,Integer id);
	public List<Users> getUsers();
	public Role addRole(Role r);
}
