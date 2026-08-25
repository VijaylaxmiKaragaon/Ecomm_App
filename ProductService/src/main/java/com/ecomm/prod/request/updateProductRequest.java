package com.ecomm.prod.request;

import lombok.Data;

@Data
public class updateProductRequest {

	private Integer productId;
	
    private String productName;
	
	private Double price;
	
	private String Description;
}
