package com.ecomm.prod.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecomm.prod.dto.BrandDto;
import com.ecomm.prod.entity.Brand;
import com.ecomm.prod.exception.AppException;
import com.ecomm.prod.repository.BrandRepository;
import com.ecomm.prod.request.addBrandRequest;
import com.ecomm.prod.request.updateBrandRequest;
import com.ecomm.prod.service.BrandService;


@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private BrandRepository brepo;
	
	@Override
	public BrandDto addBrand(addBrandRequest request) {
		Brand alreadyExist=brepo.findByBrandName(request.getBrandName()).orElse(null);
		
		if(alreadyExist != null) {
			throw new AppException("Brand Already Exist!",HttpStatus.BAD_REQUEST);
		}
		
		Brand b = mapper.map(request, Brand.class);
		b=brepo.save(b);
		BrandDto dto = mapper.map(b, BrandDto.class);
		return dto;
	}

	@Override
	public BrandDto updateBrand(Integer brandId, updateBrandRequest request) {
		Brand b = brepo.findById(brandId).orElse(null);
		
		if(b == null) {
			throw new AppException("Brand not found",HttpStatus.BAD_REQUEST);
		}
		
		mapper.map(request, b);
		System.out.println(request.getBrandName());
		System.out.println(b.getBrandName());
		
		b=brepo.save(b);
		return mapper.map(b, BrandDto.class);
	}

	@Override
	public void deleteBrandById(Integer brandId) {
		Brand b = brepo.findById(brandId).orElse(null);
		
		if(b == null) {
			throw new AppException("Brand not found", HttpStatus.BAD_REQUEST);
		}
		
		brepo.deleteById(brandId);
	}

	@Override
	public List<BrandDto> getAllBrand() {

		return brepo.findAll()
				.stream()
				.map(b->mapper.map(b, BrandDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public BrandDto getBrandById(Integer brandId) {
		Brand b = brepo.findById(brandId).orElseThrow(()-> new AppException("Brand not found",HttpStatus.BAD_REQUEST));
		BrandDto dto = mapper.map(b, BrandDto.class);
		return dto;
	}

}
