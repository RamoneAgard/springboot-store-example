package com.lrtech.lrbackend.repositories;

import com.lrtech.lrbackend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
