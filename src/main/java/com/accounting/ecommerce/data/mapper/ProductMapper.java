package com.accounting.ecommerce.data.mapper;

import com.accounting.ecommerce.data.dto.ProductDTO;
import com.accounting.ecommerce.data.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements EntityMapper<Product, ProductDTO> {

    @Override
    public ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    @Override
    public Product toEntity(ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .build();
    }
}
