package com.ecomm.prod.service;

import java.util.List;

import com.ecomm.prod.dto.ProductDto;
import com.ecomm.prod.request.addProductRequest;
import com.ecomm.prod.request.updateProductRequest;

public interface ProductService {

	ProductDto addProduct(addProductRequest request);
	
	ProductDto updateProduct(Integer productId,updateProductRequest request);
	
	ProductDto getProductById(Integer productId);
	
	void deleteProductById(Integer productId);
	
	List<ProductDto> getAllProduct();
}
