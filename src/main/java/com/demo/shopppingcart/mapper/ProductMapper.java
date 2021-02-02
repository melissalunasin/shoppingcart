package com.demo.shopppingcart.mapper;

import com.demo.shopppingcart.dto.ProductDTO;
import com.demo.shopppingcart.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<ProductDTO> productsToProductDTOs(List<Product> products);

    ProductDTO productToProductDTO(Product product);

    Product productDTOToProduct(ProductDTO productDTO);
}
