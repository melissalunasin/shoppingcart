package com.demo.shopppingcart.repository;

import com.demo.shopppingcart.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
