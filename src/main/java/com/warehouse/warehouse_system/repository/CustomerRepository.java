package com.warehouse.warehouse_system.repository;

import com.warehouse.warehouse_system.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}