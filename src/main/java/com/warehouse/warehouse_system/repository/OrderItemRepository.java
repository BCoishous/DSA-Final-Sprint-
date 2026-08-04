// repository/OrderItemRepository.java
package com.warehouse.warehouse_system.repository;

import com.warehouse.warehouse_system.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}