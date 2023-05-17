package com.application.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer UserId;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String phone;
	
	private String password;
	
	private Integer[] position;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user",fetch=FetchType.EAGER)
	private List<Role> roles= new ArrayList<>();
}
