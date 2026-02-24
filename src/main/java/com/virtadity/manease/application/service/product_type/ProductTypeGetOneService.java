package com.virtadity.manease.application.service.product_type;

import com.virtadity.manease.application.mapper.ProductTypeMapper;
import com.virtadity.manease.application.model.product_type.ProductTypeResponse;
import com.virtadity.manease.application.port.in.product_type.ProductTypeGetOneInputBoundary;
import com.virtadity.manease.application.port.out.product_type.ProductTypeGetOneOutputBoundary;
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
    public Optional<ProductTypeResponse> execute(UUID productTypeId) {
        var productType = productTypeStorageGetOne.execute(productTypeId);
        return productType.map(productTypeMapper::toProductTypeResponse);
    }
}
