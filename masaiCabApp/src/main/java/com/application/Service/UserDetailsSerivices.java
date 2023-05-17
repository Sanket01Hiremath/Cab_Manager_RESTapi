package com.application.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.application.model.Role;
import com.application.model.Users;
import com.application.repo.UsersRepo;

@Service
public class UserDetailsSerivices implements UserDetailsService{

	@Autowired
	private UsersRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> opt= userRepo.findUsersByPhone(username);

		if(opt.isPresent()) {
			//return new CustomerUserDetails(opt.get());
			Users user= opt.get();
			List<GrantedAuthority> authorities = new ArrayList<>();
		
			List<Role> roles= user.getRoles();
			for(Role r:roles) {
				SimpleGrantedAuthority sga=new SimpleGrantedAuthority(r.getName());
				//System.out.println("sga "+sga);
				authorities.add(sga);
			}
			
			//System.out.println("granted authorities "+authorities);
			return new User(user.getPhone(), user.getPassword(), authorities);	
		}else{
			throw new BadCredentialsException("User Details not found with this Phone Number: "+username);
		}
	}
}
