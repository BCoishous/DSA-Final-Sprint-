package com.warehouse.warehouse_system.controller;

import com.warehouse.warehouse_system.model.Order;
import com.warehouse.warehouse_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        try {
            Order saved = orderService.createOrder(order);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/add-to-priority-tree")
    public ResponseEntity<?> addToPriorityTree(@RequestParam Long orderId) {
        try {
            orderService.addToPriorityTree(orderId);
            return ResponseEntity.ok("Order added to priority tree");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/priority/inorder")
    public List<Order> getInorder() {
        return orderService.getInorderPriorities();
    }

    @GetMapping("/priority/highest")
    public ResponseEntity<?> getHighest() {
        try {
            return ResponseEntity.ok(orderService.getHighestPriority());
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/priority/lowest")
    public ResponseEntity<?> getLowest() {
        try {
            return ResponseEntity.ok(orderService.getLowestPriority());
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}