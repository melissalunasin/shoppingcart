package com.demo.shopppingcart.service;

import com.demo.shopppingcart.dto.CartDTO;
import com.demo.shopppingcart.mapper.CartMapper;
import com.demo.shopppingcart.model.Cart;
import com.demo.shopppingcart.repository.CartRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private CartMapper cartMapper;

    private CartRepository cartRepository;

    public CartService(CartRepository cartRepository, CartMapper cartMapper){
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    public CartDTO getCart(String id){
        Optional<Cart> cart = cartRepository.findById(id);
        if(cart.isPresent()) {
            return cartMapper.cartToCartDTO(cart.get());
        }
        throw new ResourceNotFoundException("Cart not found");
    }

    public void updateCart(String id, CartDTO cartDTO){
        Optional<Cart> cart = cartRepository.findById(id);
        if(cart.isPresent()){
            Cart cartFromDb = cart.get();

            Cart cartFromRequest = cartMapper.cartDTOToCart(cartDTO);

            //update cartItems from request
            cartFromDb.setCartItems(cartFromRequest.getCartItems());
            cartRepository.save(cartFromDb);
        }else {
            throw new IllegalArgumentException("Cart not found");
        }
    }
}
