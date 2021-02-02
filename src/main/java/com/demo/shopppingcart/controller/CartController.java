package com.demo.shopppingcart.controller;

import com.demo.shopppingcart.dto.CartDTO;
import com.demo.shopppingcart.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping(path = "/cart/{id}")
    public @ResponseBody CartDTO getCart(@PathVariable("id") String id){
        return cartService.getCart(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "/cart/{id}")
    public void updateCart(@PathVariable("id") String id, @RequestBody CartDTO cartDTO){
        cartService.updateCart(id, cartDTO);
    }
}
