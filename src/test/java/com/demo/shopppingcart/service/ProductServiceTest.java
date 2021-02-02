package com.demo.shopppingcart.service;

import com.demo.shopppingcart.dto.ProductDTO;
import com.demo.shopppingcart.dto.ProductListDTO;
import com.demo.shopppingcart.mapper.ProductListMapper;
import com.demo.shopppingcart.model.Product;
import com.demo.shopppingcart.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    public static final String PRODUCT_NAME_1 = "product1";
    public static final String PRODUCT_DESCRIPTION_1 = "description1";
    private static final String PRODUCT_NAME_2 = "product2";
    private static final String PRODUCT_DESCRIPTION_2 = "description2";

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductListMapper productListMapper;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void testGetProducts(){
        List<Product> products = createMockProducts();
        when(productRepository.findAll()).thenReturn(products);
        ProductListDTO productListDTO = createMockProductDTOs();
        when(productListMapper.productsToProductListDTO(any())).thenReturn(productListDTO);

        ProductListDTO result = productService.getProducts();
        Assert.assertArrayEquals(productListDTO.getProducts().toArray(), result.getProducts().toArray());

        verify(productRepository).findAll();
        verify(productListMapper).productsToProductListDTO(products);
    }

    private ProductListDTO createMockProductDTOs() {
        List<ProductDTO> productDTOS = new ArrayList<>();

        ProductDTO productDTO1 = ProductDTO.builder()
                .name(PRODUCT_NAME_1)
                .description(PRODUCT_DESCRIPTION_1)
                .build();
        productDTOS.add(productDTO1);

        ProductDTO productDTO2 = ProductDTO.builder()
                .name(PRODUCT_NAME_2)
                .description(PRODUCT_DESCRIPTION_2)
                .build();
        productDTOS.add(productDTO2);

        return ProductListDTO.builder()
                .products(productDTOS)
                .build();
    }

    private List<Product> createMockProducts() {
        List<Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setName(PRODUCT_NAME_1);
        product1.setDescription(PRODUCT_DESCRIPTION_1);
        products.add(product1);

        Product product2 = new Product();
        product2.setName(PRODUCT_NAME_2);
        product2.setDescription(PRODUCT_DESCRIPTION_2);
        products.add(product2);

        return products;
    }
}
