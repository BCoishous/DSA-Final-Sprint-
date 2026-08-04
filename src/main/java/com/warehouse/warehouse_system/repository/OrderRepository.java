// repository/OrderRepository.java
package com.warehouse.warehouse_system.repository;

import com.warehouse.warehouse_system.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}