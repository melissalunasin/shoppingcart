package com.demo.shopppingcart.service;

import com.demo.shopppingcart.dto.ProductListDTO;
import com.demo.shopppingcart.mapper.ProductListMapper;
import com.demo.shopppingcart.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private ProductListMapper productListMapper;

    public ProductService(ProductRepository productRepository, ProductListMapper productListMapper) {
        this.productRepository = productRepository;
        this.productListMapper = productListMapper;
    }

    public ProductListDTO getProducts() {
        return productListMapper.productsToProductListDTO(productRepository.findAll());
    }
}
