package com.ecomm.prod.service;

import java.util.List;

import com.ecomm.prod.dto.BrandDto;
import com.ecomm.prod.dto.ProductDto;
import com.ecomm.prod.request.addBrandRequest;
import com.ecomm.prod.request.updateBrandRequest;
import com.ecomm.prod.request.updateProductRequest;

public interface BrandService {

	BrandDto addBrand(addBrandRequest request);
	
    BrandDto updateBrand(Integer brandId,updateBrandRequest request);
	
	BrandDto getBrandById(Integer brandId);
	
	void deleteBrandById(Integer brandId);
	
	List<BrandDto> getAllBrand();
}
