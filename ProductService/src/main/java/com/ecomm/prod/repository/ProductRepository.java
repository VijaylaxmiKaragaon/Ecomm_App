package com.ecomm.prod.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecomm.prod.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query("SELECT p FROM Product p WHERE p.productName=:productName")
	Optional<Product> findByProductName(String productName);
}
