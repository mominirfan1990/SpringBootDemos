package com.irfan.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.irfan.spring.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

       public List<Product> findByNameContainingIgnoreCase(String searchString);


    @Query("select p from Product p where p.name = :name")
    public List<Product> findByNameIs(@Param("name") String name);

}