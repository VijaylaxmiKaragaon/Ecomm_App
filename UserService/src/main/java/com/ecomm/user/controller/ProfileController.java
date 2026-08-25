package com.ecomm.user.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecomm.user.dto.ProfileDto;
import com.ecomm.user.request.ProfileUpdateRequest;
import com.ecomm.user.response.ApiResponse;
import com.ecomm.user.service.ProfileService;




@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	private ProfileService pservice;

	@PutMapping(value="/update/{profileId}",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> updateProfile(@PathVariable Integer profileId,
										 
										   @RequestPart(value="image",required = false) MultipartFile image,
										     @RequestParam String firstName,@RequestParam String lastName,@RequestParam LocalDate dob,@RequestParam String phone){
		ProfileUpdateRequest p=new ProfileUpdateRequest();
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setDob(dob);
		p.setPhone(phone);
		ProfileDto dto=pservice.updateProfile(profileId, p, image);
		return ResponseEntity.ok(new ApiResponse<>("Profile updated successfully!",dto,HttpStatus.OK));
	}
	
}
