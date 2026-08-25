package com.ecomm.user.service;

import org.springframework.web.multipart.MultipartFile;

import com.ecomm.user.dto.ProfileDto;
import com.ecomm.user.entity.Profile;
import com.ecomm.user.request.ProfileUpdateRequest;

public interface ProfileService {

	Profile addProfile(Profile profile);
	
//	void updateProfile(Integer profileId, UpdateRequest request);
	
	void deleteProfile(Integer profileId);
	
	ProfileDto getProfileById(Integer profileId);
	
	ProfileDto getProfileByUserId(Integer userId);

	ProfileDto updateProfile(Integer profileId, ProfileUpdateRequest request, MultipartFile image);
}
