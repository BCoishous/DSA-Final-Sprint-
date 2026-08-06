package com.warehouse.warehouse_system.controller;

import com.warehouse.warehouse_system.model.Customer;
import com.warehouse.warehouse_system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        try {
            Customer saved = customerService.createCustomer(customer);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}