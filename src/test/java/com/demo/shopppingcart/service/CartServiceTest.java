package com.demo.shopppingcart.service;

import com.demo.shopppingcart.dto.CartDTO;
import com.demo.shopppingcart.mapper.CartMapper;
import com.demo.shopppingcart.model.Cart;
import com.demo.shopppingcart.repository.CartRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    private static final String ID = "1";

    @InjectMocks
    private CartService cartService;

    @Mock
    private CartMapper cartMapper;

    @Mock
    private CartRepository cartRepository;

    @Test
    public void testGetCart_WithResult(){
        Cart cart = new Cart();
        when(cartRepository.findById(any())).thenReturn(Optional.of(cart));

        CartDTO cartDTO = CartDTO.builder().build();
        when(cartMapper.cartToCartDTO(any())).thenReturn(cartDTO);

        CartDTO result = cartService.getCart(ID);

        assertEquals(cartDTO, result);

        verify(cartRepository).findById(ID);
        verify(cartMapper).cartToCartDTO(cart);
    }

    @Test
    public void testGetCart_WithoutResult(){
        when(cartRepository.findById(any())).thenReturn(Optional.empty());

        CartDTO result = cartService.getCart(ID);

        assertNull(result);

        verify(cartRepository).findById(ID);
        verify(cartMapper, times(0)).cartToCartDTO(any());
    }

}
