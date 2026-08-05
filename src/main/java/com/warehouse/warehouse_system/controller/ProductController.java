package com.warehouse.warehouse_system.controller;

import com.warehouse.warehouse_system.model.Product;
import com.warehouse.warehouse_system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            Product saved = productService.createProduct(product);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/sorted")
    public ResponseEntity<?> getSorted(@RequestParam String by) {
        List<Product> products = productService.getAllProducts();

        switch (by) {
            case "price":
                return ResponseEntity.ok(productService.sortByPrice(products));
            case "stock":
                return ResponseEntity.ok(productService.sortByStock(products));
            default:
                return ResponseEntity.badRequest().body("Invalid sort field. Use 'price' or 'stock'.");
        }
    }
}