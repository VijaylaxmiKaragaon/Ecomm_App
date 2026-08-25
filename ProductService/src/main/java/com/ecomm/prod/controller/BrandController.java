package com.ecomm.prod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.prod.dto.BrandDto;
import com.ecomm.prod.dto.ProductDto;
import com.ecomm.prod.request.addBrandRequest;
import com.ecomm.prod.request.addProductRequest;
import com.ecomm.prod.response.ApiResponse;
import com.ecomm.prod.service.BrandService;

@RestController
@RequestMapping("/brand")
public class BrandController {

	@Autowired
	private BrandService bservice;
	
	@PostMapping("/addBrand")
	public ResponseEntity<?> addBrand(@RequestBody addBrandRequest request){
		BrandDto dto=bservice.addBrand(request);
		return ResponseEntity.ok(new ApiResponse<>("Data added successfully",dto,HttpStatus.OK));
	}
	
	@PostMapping("/get/{bid}")
	public ResponseEntity<?> getByid(@PathVariable Integer bid){
		BrandDto dto = bservice.getBrandById(bid);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping("/delete/{bid}")
	public ResponseEntity<?> delete(@PathVariable Integer bid){
		bservice.deleteBrandById(bid);
		return ResponseEntity.ok("delete sucessfull");
	}
}
