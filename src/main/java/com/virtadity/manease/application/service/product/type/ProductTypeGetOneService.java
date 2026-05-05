package com.virtadity.manease.application.service.product.type;

import com.virtadity.manease.application.mapper.ProductTypeMapper;
import com.virtadity.manease.application.model.product.type.ProductTypeResponse;
import com.virtadity.manease.application.port.in.product.type.ProductTypeGetOneInputBoundary;
import com.virtadity.manease.application.port.out.product.type.ProductTypeGetOneOutputBoundary;
import com.virtadity.manease.application.service.product.type.exception.ProductTypeNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
