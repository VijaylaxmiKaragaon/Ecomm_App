package com.ecomm.prod.request;

import lombok.Data;

@Data
public class addProductRequest {

	private String productName;
	
	private Double price;
	
	private String description;
	
}
