package com.example.bdccensetspringenset.repository;

import com.example.bdccensetspringenset.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
