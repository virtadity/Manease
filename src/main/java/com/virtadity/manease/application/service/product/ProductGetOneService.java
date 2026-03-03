package com.virtadity.manease.application.service.product;

import com.virtadity.manease.application.mapper.ProductMapper;
import com.virtadity.manease.application.model.product.ProductResponse;
import com.virtadity.manease.application.port.in.product.ProductGetOneInputBoundary;
import com.virtadity.manease.application.port.out.product.ProductGetOneOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductGetOneService implements ProductGetOneInputBoundary {
    private final ProductGetOneOutputBoundary productStorageGetOne;
    private final ProductMapper productMapper;

    @Override
    public Optional<ProductResponse> execute(UUID productId) {
        var product = productStorageGetOne.getOne(productId);
        return product.map(productMapper::toProductResponse);
    }
}
