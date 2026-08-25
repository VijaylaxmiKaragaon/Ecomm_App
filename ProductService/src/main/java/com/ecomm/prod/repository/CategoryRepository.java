package com.ecomm.prod.repository;

import java.util.Locale.Category;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CategoryRepository extends JpaRepository<Category, Integer>{

	@Query("SELECT c FROM Category c WHERE c.categoryName=:categoryName")
	Optional<Category> findByCategoryName(String categoryName);
}
