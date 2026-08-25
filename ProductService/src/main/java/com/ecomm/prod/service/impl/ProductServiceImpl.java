package com.ecomm.prod.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cloudinary.api.exceptions.ApiException;
import com.ecomm.prod.dto.ProductDto;
import com.ecomm.prod.entity.Product;
import com.ecomm.prod.exception.AppException;
import com.ecomm.prod.repository.ProductRepository;
import com.ecomm.prod.request.addProductRequest;
import com.ecomm.prod.request.updateProductRequest;
import com.ecomm.prod.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ProductRepository prepo;
	
	@Override
	public ProductDto addProduct(addProductRequest request) {
		Product alreadyExist=prepo.findByProductName(request.getProductName()).orElse(null);
		
		if(alreadyExist != null) {
			throw new AppException("Product Already Exist!",HttpStatus.BAD_REQUEST);
		}
		Product p=mapper.map(request, Product.class);
		p = prepo.save(p);
		ProductDto dto = mapper.map(p, ProductDto.class);
		return dto;
	}

	@Override
	public ProductDto getProductById(Integer productId) {
		Product p = prepo.findById(productId).orElseThrow(()-> new AppException("Product not found", HttpStatus.BAD_REQUEST));
		ProductDto dto = mapper.map(p, ProductDto.class);
		return dto;
	}

	@Override
	public void deleteProductById(Integer productId) {
		Product p = prepo.findById(productId).orElse(null);
		
		if(p == null) {
			throw new AppException("Product not found",HttpStatus.BAD_REQUEST);
		}
		
		prepo.deleteById(productId);
		
	}

	@Override
	public List<ProductDto> getAllProduct() {
		
		return prepo.findAll()
				.stream()
				.map(p->mapper.map(p, ProductDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ProductDto updateProduct(Integer productId,updateProductRequest request) {
		Product p = prepo.findById(productId).orElse(null);
		if(p == null) {
			throw new AppException("Product not found",HttpStatus.BAD_REQUEST);
		}
		
		mapper.map(request,p);
		System.out.println(request.getProductName());
		System.out.println(p.getProductName());
		
		p=prepo.save(p);
		return mapper.map(p, ProductDto.class);
	}

}
