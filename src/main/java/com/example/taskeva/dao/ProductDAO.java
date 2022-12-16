package com.example.taskeva.dao;

import com.example.taskeva.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product,Long> {
}
