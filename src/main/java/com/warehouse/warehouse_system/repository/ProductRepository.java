package com.warehouse.warehouse_system.repository;

import com.warehouse.warehouse_system.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}