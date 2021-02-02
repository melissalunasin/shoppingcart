package com.demo.shopppingcart.controller;

import com.demo.shopppingcart.dto.ProductDTO;
import com.demo.shopppingcart.dto.ProductListDTO;
import com.demo.shopppingcart.service.ProductService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    private static final String PRODUCT_NAME = "product1";
    private static final String PRODUCT_DESCRIPTION = "description1";
    public static final String PRODUCT_URL = "/product";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetProducts() throws Exception {
        ProductListDTO mockProductListDTO = buildMockProductListDTO();
        when(productService.getProducts()).thenReturn(mockProductListDTO);

        String responseBodyString = this.mockMvc.perform(
                get(PRODUCT_URL))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JSONObject jsonObject = new JSONObject(responseBodyString);
        JSONAssert.assertEquals("{\"products\":[{\"name\":\"" + PRODUCT_NAME + "\",\"description\":"
                + "\"" + PRODUCT_DESCRIPTION + "\"}]}", jsonObject, true);

    }

    private ProductListDTO buildMockProductListDTO() {
        List<ProductDTO> products = new ArrayList<>();
        ProductDTO productDTO1 = ProductDTO.builder()
                .name(PRODUCT_NAME)
                .description(PRODUCT_DESCRIPTION)
                .build();
        products.add(productDTO1);

        return ProductListDTO.builder()
                .products(products)
                .build();
    }
}
