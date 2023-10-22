package com.accounting.ecommerce.service;

import com.accounting.ecommerce.data.dto.ProductDTO;
import com.accounting.ecommerce.request.PaginationRequest;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductDTO create(ProductDTO productDTO);

    ProductDTO update(ProductDTO productDTO, String id);

    ProductDTO getById(String id);

    Page<ProductDTO> getAll(PaginationRequest paginationRequest);

    void deleteById(String id);
}
