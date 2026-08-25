package com.ecomm.user.request;

import java.time.LocalDate;

import com.ecomm.user.enums.RoleType;

import lombok.Data;

@Data
public class RegisterRequest {

	private String email;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private LocalDate dob;
	
	private String phone;
	
	private RoleType roleName;
}
