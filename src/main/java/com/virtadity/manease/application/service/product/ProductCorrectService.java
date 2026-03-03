package com.virtadity.manease.application.service.product;

import com.virtadity.manease.application.mapper.ProductMapper;
import com.virtadity.manease.application.model.product.ProductRequest;
import com.virtadity.manease.application.model.product.ProductResponse;
import com.virtadity.manease.application.port.in.product.ProductCorrectInputBoundary;
import com.virtadity.manease.application.port.out.product.ProductCorrectOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductCorrectService implements ProductCorrectInputBoundary {
    private final ProductCorrectOutputBoundary productStorageCorrect;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse execute(UUID productId, ProductRequest productRequest) {
        var product = productMapper.toProduct(productRequest);
        var correctedProduct = productStorageCorrect.correct(productId, product);
        return productMapper.toProductResponse(correctedProduct);
    }
}
