package com.project.management.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.project.management.entity.*;
import com.project.management.repo.*;
import com.project.management.exception.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepo ProductRepo;

    @Autowired
    private CategoryRepo CategoryRepo;

    public Product createProduct(Product product, Long categoryId) {

        Category category = CategoryRepo.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        product.setCategory(category);
        return ProductRepo.save(product);
    }

    public List<Product> getAllProducts() {
        return ProductRepo.findAll();
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        return ProductRepo.findByCategoryCategoryId(categoryId);
    }

    public List<Product> searchProductByName(String name) {
        return ProductRepo.findByProductNameContainingIgnoreCase(name);
    }

    public void deleteProduct(Long id) {
        if (!ProductRepo.existsById(id)) {
            throw new ProductNotFoundException("Product not found");
        }
        ProductRepo.deleteById(id);
    }
}