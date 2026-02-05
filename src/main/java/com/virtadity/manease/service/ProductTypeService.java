package com.virtadity.manease.service;

import com.virtadity.manease.dto.ProductTypeDTO;

import java.util.List;
import java.util.Optional;

public interface ProductTypeService {
    List<ProductTypeDTO> all();
    Optional<ProductTypeDTO> one(Long id);
    ProductTypeDTO store(ProductTypeDTO productTypeDTO, Optional<Long> id );
    void delete(Long id);
}
