package com.ecomm.prod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.prod.dto.ProductDto;
import com.ecomm.prod.request.addProductRequest;
import com.ecomm.prod.response.ApiResponse;
import com.ecomm.prod.service.ProductService;


import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService pservice;
	
	@PostMapping("/addProduct")
	public ResponseEntity<?> addProduct(@RequestBody addProductRequest request){
		ProductDto dto=pservice.addProduct(request);
		return ResponseEntity.ok(new ApiResponse<>("Data added successfully",dto,HttpStatus.OK));
	}
	
	@PostMapping("/get/{pid}")
	public ResponseEntity<?> getByid(@PathVariable Integer pid){
		ProductDto dto = pservice.getProductById(pid);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping("/delete/{pid}")
	public ResponseEntity<?> delete(@PathVariable Integer pid){
		pservice.deleteProductById(pid);
		return ResponseEntity.ok("delete sucessfull");
	}

}
