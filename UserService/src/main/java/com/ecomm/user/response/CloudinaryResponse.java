package com.ecomm.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CloudinaryResponse {

	private String imageUrl;
	
	private String publicId;
}
