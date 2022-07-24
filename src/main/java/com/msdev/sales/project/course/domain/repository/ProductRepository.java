package com.msdev.sales.project.course.domain.repository;

import com.msdev.sales.project.course.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
