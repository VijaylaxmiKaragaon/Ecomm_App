package com.ecomm.prod.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {

	private Integer categoryId;
	
	private String categoryName;
	
	private String imageUrl;
	
	private String discription;
}
