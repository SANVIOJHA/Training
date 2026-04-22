package com.project.management.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.project.management.entity.Category;
import com.project.management.repo.CategoryRepo;
import com.project.management.exception.CategoryNotFoundException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo CategoryRepo;

    public Category createCategory(Category category) {
        if (CategoryRepo.existsByCategoryName(category.getCategoryName())) {
            throw new RuntimeException("Category name must be unique");
        }
        return CategoryRepo.save(category);
    }

    public List<Category> getAllCategories() {
        return CategoryRepo.findAll();
    }

    public Category getCategoryById(Long id) {
        return CategoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }

    public void deleteCategory(Long id) {
        if (!CategoryRepo.existsById(id)) {
            throw new CategoryNotFoundException("Category not found");
        }
        CategoryRepo.deleteById(id);
    }
}