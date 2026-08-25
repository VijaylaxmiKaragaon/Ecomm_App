package com.ecomm.prod.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecomm.prod.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{

	@Query("SELECT b FROM Brand b WHERE b.brandName=:brandName")
	Optional<Brand> findByBrandName(String brandName);
}
