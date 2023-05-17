package com.application.model;

import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer driverId;
	private Integer Car;
	
	private Integer[] position;
}
