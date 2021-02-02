package com.demo.shopppingcart.mapper;

import com.demo.shopppingcart.dto.ProductListDTO;
import com.demo.shopppingcart.model.Product;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public abstract class ProductListMapper {

    @Autowired
    private ProductMapper productMapper;

    public ProductListDTO productsToProductListDTO(List<Product> products){
        return ProductListDTO.builder()
                .products(productMapper.productsToProductDTOs(products))
                .build();
    }
}
