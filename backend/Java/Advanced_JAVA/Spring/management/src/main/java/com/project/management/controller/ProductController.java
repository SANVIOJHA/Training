package com.project.management.controller;


import com.project.management.entity.Product;
import com.project.management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.project.management.entity.Category;
import com.project.management.service.CategoryService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/{categoryId}")
    public Product createProduct(@RequestBody Product product,
                                 @PathVariable Long categoryId) {
        return productService.createProduct(product, categoryId);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    @GetMapping("/search")
    public List<Product> searchProduct(@RequestParam String name) {
        return productService.searchProductByName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}