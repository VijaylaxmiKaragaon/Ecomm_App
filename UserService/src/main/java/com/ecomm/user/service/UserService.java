package com.ecomm.user.service;

import java.util.List;

import com.ecomm.user.dto.UserDto;
import com.ecomm.user.request.LoginRequest;
import com.ecomm.user.request.LoginRequest;
import com.ecomm.user.request.RegisterRequest;

public interface UserService {

	UserDto register(RegisterRequest request);
	
	UserDto login(LoginRequest request);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUser();
	
//	void updateUser(Integer userId, UpdateRequest request);
	
	void deleteUserById(Integer userId);
}
