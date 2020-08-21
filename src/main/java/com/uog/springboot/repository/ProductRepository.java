package com.uog.springboot.repository;

import com.uog.springboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    public List<Product> findByProductIsActive(String ISACTIVE);

}