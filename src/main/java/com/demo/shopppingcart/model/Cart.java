package com.demo.shopppingcart.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
@Setter
@Getter
public class Cart {

    @Id
    private String id;

    private Set<CartItem> cartItems;

    public Cart(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Cart() {
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", cartItems=" + cartItems +
                '}';
    }
}
