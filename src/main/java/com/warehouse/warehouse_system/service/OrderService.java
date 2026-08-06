package com.warehouse.warehouse_system.service;

import com.warehouse.warehouse_system.bst.OrderBST;
import com.warehouse.warehouse_system.model.Order;
import com.warehouse.warehouse_system.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private final OrderBST priorityTree = new OrderBST();

    public Order createOrder(Order order) {
        if (order.getCustomer() == null) {
            throw new IllegalArgumentException("Order must have a customer");
        }
        if (order.getPriorityLevel() < 1 || order.getPriorityLevel() > 10) {
            throw new IllegalArgumentException("Priority level must be between 1 and 10");
        }

        Order saved = orderRepository.save(order);
        priorityTree.insert(saved);
        return saved;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void addToPriorityTree(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id " + orderId));
        priorityTree.insert(order);
    }

    public List<Order> getInorderPriorities() {
        return priorityTree.inorder();
    }

    public Order getHighestPriority() {
        return priorityTree.findHighest();
    }

    public Order getLowestPriority() {
        return priorityTree.findLowest();
    }
}