package com.demo.shopppingcart.repository;

import com.demo.shopppingcart.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {
}
