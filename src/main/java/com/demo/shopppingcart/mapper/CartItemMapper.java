package com.demo.shopppingcart.mapper;

import com.demo.shopppingcart.dto.CartItemDTO;
import com.demo.shopppingcart.model.CartItem;
import org.mapstruct.Mapper;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface CartItemMapper {

    Set<CartItemDTO> cartItemsToCartItemDTOs(Set<CartItem> cartItems);

    CartItemDTO cartItemToCartItemDTO(CartItem cartItem);

    Set<CartItem> cartItemDTOsToCartItems(Set<CartItemDTO> cartItemDTOs);

    CartItem cartItemDTOToCartItem(CartItemDTO cartItemDTO);
}
