package com.ecomm.prod.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductDto {

	private Integer productId;
	
	private String description;
	
	private Double price;
}
