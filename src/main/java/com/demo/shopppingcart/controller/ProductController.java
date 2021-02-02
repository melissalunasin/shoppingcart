package com.demo.shopppingcart.controller;

import com.demo.shopppingcart.dto.ProductListDTO;
import com.demo.shopppingcart.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping(path = "/product")
    public @ResponseBody ProductListDTO getProducts(){
        return productService.getProducts();
    }
}
