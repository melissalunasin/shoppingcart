package com.demo.shopppingcart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.demo.shopppingcart.model.CartItem;

public interface CartItemRepository extends MongoRepository<CartItem, String> {

}
