package com.ecomm.prod.request;

import lombok.Data;

@Data
public class updateBrandRequest {

	private Integer brandId;
	
	private String brandName;
	
	private String imageUrl;
}
