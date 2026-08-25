package com.ecomm.user.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecomm.user.dto.ProfileDto;
import com.ecomm.user.dto.RoleDto;
import com.ecomm.user.dto.UserDto;
import com.ecomm.user.entity.Profile;
import com.ecomm.user.entity.Role;
import com.ecomm.user.entity.User;
import com.ecomm.user.exception.AppException;
import com.ecomm.user.repository.UserRepository;
import com.ecomm.user.request.LoginRequest;
import com.ecomm.user.request.RegisterRequest;
import com.ecomm.user.service.ProfileService;
import com.ecomm.user.service.RoleService;
import com.ecomm.user.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository urepo;
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ProfileService pservice;
	
	@Autowired
	private RoleService rservice;
	
	@Transactional
	@Override
	public UserDto register(RegisterRequest request) {
		
		RoleDto existingRole=rservice.getRoleByRoleName(request.getRoleName());
		
		if(existingRole==null) {
			throw new AppException("Role Not Found!", HttpStatus.NOT_FOUND);
		}
		
		
		User alreadyExists = urepo.findByEmail(request.getEmail()).orElse(null);
		
		
		if (alreadyExists != null) {
			throw new AppException("User already exists!", HttpStatus.BAD_REQUEST);
		}
		User u = mapper.map(request, User.class);
		Role r=mapper.map(existingRole, Role.class);
		u.setRole(r);
		u = urepo.save(u);
		Profile p=mapper.map(request, Profile.class);
		p.setUser(u);
		p=pservice.addProfile(p);
		
		
		UserDto dto = mapper.map(u, UserDto.class);
		ProfileDto pdto=mapper.map(p, ProfileDto.class);
		dto.setProfileDto(pdto);
		dto.setRoleDto(existingRole);
		
		return dto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User u = urepo.findById(userId).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
		ProfileDto pdto=pservice.getProfileByUserId(userId);
		RoleDto rdto=rservice.getRoleById(userId);
		UserDto dto=mapper.map(u, UserDto.class);
		dto.setProfileDto(pdto);
		dto.setRoleDto(rdto);
		
		return dto;
	}

	@Override
	public List<UserDto> getAllUser() {

		return urepo.findAll()
				.stream()
				.map(u->mapper.map(u, UserDto.class))
				.collect(Collectors.toList());
	}
	

	public void deleteUserById(Integer userId) {
		User u = urepo.findById(userId).orElse(null);
		if (u == null) {
			throw new AppException("user not found", HttpStatus.NOT_FOUND);
			
		}
		pservice.deleteProfile(userId);
		urepo.deleteById(userId);
		

	}
//
//	@Override
//	public UserDto login(LoginRequest request) {
//		User alreadyExists = urepo.findByEmail(request.getEmail()).orElse(null);
//		// Email Validation
//		if (alreadyExists == null) {
//			throw new AppException("User not found!", HttpStatus.NOT_FOUND);
//		}
//		// Password Validation
//		if (!alreadyExists.getPassword().equals(request.getPassword())) {
//			throw new AppException("Incorrect credentials", HttpStatus.BAD_REQUEST);
//		}
//                       
//		UserDto dto = mapper.map(alreadyExists, UserDto.class);
//		ProfileDto pdto=pservice.getProfileByUserId(dto.getUserId());
//		dto.setProfileDto(pdto);
//		return dto;
//
//	}
	
	@Override
	public UserDto login(LoginRequest request) {

	    User alreadyExists = urepo.findByEmail(request.getEmail()).orElse(null);

	    // Email validation
	    if (alreadyExists == null) {
	        throw new AppException("User not found!", HttpStatus.NOT_FOUND);
	    }

	    // Password validation
	    if (!alreadyExists.getPassword().equals(request.getPassword())) {
	        throw new AppException("Incorrect credentials", HttpStatus.BAD_REQUEST);
	    }

	    UserDto dto = mapper.map(alreadyExists, UserDto.class);

	    // Profile mapping
	    ProfileDto pdto = pservice.getProfileByUserId(dto.getUserId());
	    dto.setProfileDto(pdto);

	    // Role mapping
	    if (alreadyExists.getRole() != null) {
	        RoleDto rdto = rservice.getRoleById(
	                alreadyExists.getRole().getRoleId()
	        );

	        dto.setRoleDto(rdto);
	    }

	    return dto;
	}

}
