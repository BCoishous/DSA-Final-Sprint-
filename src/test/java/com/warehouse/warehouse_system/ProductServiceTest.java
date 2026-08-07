package com.warehouse.warehouse_system;

import com.warehouse.warehouse_system.model.Product;
import com.warehouse.warehouse_system.service.ProductService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    @Test
    void sortByPriceOrdersProductsAscending() {
        // Arrange
        ProductService productService = new ProductService();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Gizmo", 99.00, 10));
        products.add(new Product("Gadget", 5.50, 200));
        products.add(new Product("Widget", 19.99, 50));

        // Act
        List<Product> sorted = productService.sortByPrice(products);

        // Assert
        assertEquals("Gadget", sorted.get(0).getName());
        assertEquals("Widget", sorted.get(1).getName());
        assertEquals("Gizmo", sorted.get(2).getName());
    }
}