package com.warehouse.warehouse_system.service;

import com.warehouse.warehouse_system.model.Product;
import com.warehouse.warehouse_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        return productRepository.save(product);
    }

    public List<Product> sortByPrice(List<Product> products) {
        return insertionSort(products, Comparator.comparingDouble(Product::getPrice));
    }

    public List<Product> sortByStock(List<Product> products) {
        return insertionSort(products, Comparator.comparingInt(Product::getStock));
    }

    private List<Product> insertionSort(List<Product> products, Comparator<Product> comparator) {
        for (int i = 1; i < products.size(); i++) {
            Product current = products.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(products.get(j), current) > 0) {
                products.set(j + 1, products.get(j));
                j--;
            }
            products.set(j + 1, current);
        }
        return products;
    }
}