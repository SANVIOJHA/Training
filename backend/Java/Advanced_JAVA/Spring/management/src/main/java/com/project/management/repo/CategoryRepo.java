package com.project.management.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import com.project.management.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    boolean existsByCategoryName(String categoryName);
}