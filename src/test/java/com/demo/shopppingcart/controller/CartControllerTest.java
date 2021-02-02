package com.demo.shopppingcart.controller;

import com.demo.shopppingcart.dto.CartDTO;
import com.demo.shopppingcart.dto.CartItemDTO;
import com.demo.shopppingcart.dto.ProductDTO;
import com.demo.shopppingcart.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    public static final String CART_URL = "/cart/1";
    public static final String PRODUCT_NAME = "product1";
    public static final String PRODUCT_DESCRIPTION = "description1";
    public static final int PRODUCT_QUANTITY = 1;
    private static final String ID = "1";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetCart_WithResult() throws Exception {
        when(cartService.getCart(ID)).thenReturn(buildMockCartDTO());

        String responseBodyString = this.mockMvc.perform(
                get(CART_URL))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JSONObject jsonObject = new JSONObject(responseBodyString);
        JSONAssert.assertEquals("{\"items\":[{\"product\":{\"name\":\"" + PRODUCT_NAME + "\",\"description\":"
                + "\"" + PRODUCT_DESCRIPTION + "\"},\"quantity\": " + PRODUCT_QUANTITY + "}]}", jsonObject, true);
    }

    private CartDTO buildMockCartDTO() {
        List<CartItemDTO> items = new ArrayList<>();
        CartItemDTO cartItemDTO1 = CartItemDTO.builder()
                .product(ProductDTO.builder()
                        .name(PRODUCT_NAME)
                        .description(PRODUCT_DESCRIPTION)
                        .build())
                .quantity(PRODUCT_QUANTITY)
                .build();
        items.add(cartItemDTO1);

        CartDTO cartDTO = CartDTO.builder()
                .items(items)
                .build();

        return cartDTO;
    }

    @Test
    public void testGetCart_WithoutResults() throws Exception {
        when(cartService.getCart(any())).thenThrow(new ResourceNotFoundException());

        this.mockMvc.perform(
                get(CART_URL))
                .andExpect(status().isNotFound());

        verify(cartService).getCart(ID);
    }

    @Test
    public void testUpdateCart_SuccessfulUpdate() throws Exception {
        doNothing().when(cartService).updateCart(any(), any());

        CartDTO cartDTO = buildMockCartDTO();
        String content = objectMapper.writeValueAsString(cartDTO);
        this.mockMvc.perform(
                put(CART_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isNoContent());

        verify(cartService).updateCart(eq(ID),
                argThat(cartDTORequested -> {
                    return cartDTO.equals(cartDTORequested);
                } )
        );
    }

    @Test
    public void testUpdateCart_NotFound() throws Exception {
        doThrow(new ResourceNotFoundException()).when(cartService).updateCart(any(), any());

        CartDTO cartDTO = buildMockCartDTO();
        String content = objectMapper.writeValueAsString(cartDTO);
        this.mockMvc.perform(
                put(CART_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isNotFound());

        verify(cartService).updateCart(eq(ID),
                argThat(cartDTORequested -> {
                    return cartDTO.equals(cartDTORequested);
                }));
    }
}
