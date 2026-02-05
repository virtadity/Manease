package com.virtadity.manease.service;

import com.virtadity.manease.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDTO> all();
    Optional<ProductDTO> one(Long id);
    ProductDTO store(ProductDTO productDTO, Optional<Long> id);
    void delete(Long id);
}
