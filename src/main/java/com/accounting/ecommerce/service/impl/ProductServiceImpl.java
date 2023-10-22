package com.accounting.ecommerce.service.impl;

import com.accounting.ecommerce.data.dto.ProductDTO;
import com.accounting.ecommerce.data.entity.Product;
import com.accounting.ecommerce.data.mapper.ProductMapper;
import com.accounting.ecommerce.exception.ProductNotFoundException;
import com.accounting.ecommerce.repository.ProductRepository;
import com.accounting.ecommerce.request.PaginationRequest;
import com.accounting.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        return productMapper.toDTO(productRepository.save(product));
    }

    @CacheEvict(value = "product", key = "#id")
    @Override
    public void deleteById(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    @Cacheable(value = "product", key = "#id")
    @Override
    public ProductDTO getById(String id) {
        return productRepository.findById(id).map(productMapper::toDTO).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Page<ProductDTO> getAll(PaginationRequest paginationRequest) {
        return productRepository.findAll(PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize())).map(productMapper::toDTO);
    }

    @CachePut(value = "product", key = "#id")
    @Override
    public ProductDTO update(ProductDTO productDTO, String id) {
        Product product = productMapper.toEntity(productDTO);
        product.setId(id);

        return productMapper.toDTO(productRepository.save(product));

    }
}
