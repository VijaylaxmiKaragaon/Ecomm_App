package com.ecomm.prod.request;

import lombok.Data;

@Data
public class updateCategoryRequest {

	private String categoryName;
	
	private String imageUrl;
	
	private String discription;
}
