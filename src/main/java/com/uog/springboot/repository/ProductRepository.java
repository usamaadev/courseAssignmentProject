package com.uog.springboot.repository;

import com.uog.springboot.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from product where isactive='Y';", nativeQuery = true)
    public List<Product> findActive();

}