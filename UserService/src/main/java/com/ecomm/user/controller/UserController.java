package com.ecomm.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.user.dto.UserDto;
import com.ecomm.user.request.LoginRequest;
import com.ecomm.user.request.RegisterRequest;
import com.ecomm.user.response.ApiResponse;
import com.ecomm.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService uservice;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request){
		UserDto dto=uservice.register(request);
		return ResponseEntity.ok(new ApiResponse<>("Data added successfully",dto,HttpStatus.OK));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request){
		UserDto dto = uservice.login(request);
		return ResponseEntity.ok(new ApiResponse<>("Login successfull", dto, HttpStatus.OK));
	}
	
	@DeleteMapping("/delete/{uid}")
	public ResponseEntity<?>deletee( @PathVariable Integer uid){
		uservice.deleteUserById(uid);
		return ResponseEntity.ok("delete sucessfull");
	}
	
	@GetMapping("/get/{uid}")
	public ResponseEntity<?>getbyid( @PathVariable Integer uid){
		   UserDto udto=uservice.getUserById(uid);
		   return ResponseEntity.ok(udto);
	}
}
