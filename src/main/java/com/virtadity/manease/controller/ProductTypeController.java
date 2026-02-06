package com.virtadity.manease.controller;


import com.virtadity.manease.dto.ProductTypeDTO;

import java.util.List;
import java.util.Optional;

public interface ProductTypeController {
    List<ProductTypeDTO> all();
    Optional<ProductTypeDTO> one(Long id);
    ProductTypeDTO create(ProductTypeDTO productTypeDTO);
    ProductTypeDTO update(Long id, ProductTypeDTO productTypeDTO);
    void delete(Long id);
}
