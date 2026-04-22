package com.project.management.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import com.project.management.entity.Product;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findByProductNameContainingIgnoreCase(String productName);

    List<Product> findByCategoryCategoryId(Long categoryId);
}