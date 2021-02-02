package com.demo.shopppingcart.mapper;

import com.demo.shopppingcart.dto.CartDTO;
import com.demo.shopppingcart.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CartItemMapper.class})
public interface CartMapper {

    @Mapping(source = "cartItems", target = "items")
    CartDTO cartToCartDTO(Cart cart);

    @Mapping(source = "items", target = "cartItems")
    Cart cartDTOToCart(CartDTO cartDTO);
}
