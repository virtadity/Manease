package com.virtadity.manease.application.service.product_type;

import com.virtadity.manease.application.mapper.ProductTypeMapper;
import com.virtadity.manease.application.model.product_type.ProductTypeResponse;
import com.virtadity.manease.application.port.in.product_type.ProductTypeGetOneInputBoundary;
import com.virtadity.manease.application.port.out.product_type.ProductTypeGetOneOutputBoundary;
import com.virtadity.manease.application.service.product_type.exception.ProductTypeNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductTypeGetOneService implements ProductTypeGetOneInputBoundary {
    private final ProductTypeGetOneOutputBoundary productTypeStorageGetOne;
    private final ProductTypeMapper productTypeMapper;

    @Override
    public ProductTypeResponse execute(UUID productTypeId) {
        var productType = productTypeStorageGetOne
                .getOne(productTypeId)
                .orElseThrow(() -> ProductTypeNotFoundException.withId(productTypeId));
        return productTypeMapper.toProductTypeResponse(productType);
    }
}
