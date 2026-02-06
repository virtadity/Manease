package com.virtadity.manease.controller;

import com.virtadity.manease.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductController {

    List<ProductDTO> all();
    Optional<ProductDTO> one(Long id);
    ProductDTO create(ProductDTO productDTO);
    ProductDTO update(Long id, ProductDTO productDTO);
    void delete(Long id);
}
