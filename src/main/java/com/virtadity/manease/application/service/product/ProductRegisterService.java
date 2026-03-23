package com.virtadity.manease.application.service.product;

import com.virtadity.manease.application.mapper.ProductMapper;
import com.virtadity.manease.application.model.product.ProductRequest;
import com.virtadity.manease.application.model.product.ProductResponse;
import com.virtadity.manease.application.port.in.product.ProductRegisterInputBoundary;
import com.virtadity.manease.application.port.out.product.ProductCreateOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductRegisterService  implements ProductRegisterInputBoundary {
    private final ProductCreateOutputBoundary productStorageRegister;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse execute(ProductRequest productRequest) {
        var product = productMapper.toProduct(productRequest);
        var savedProduct = productStorageRegister.create(product);
        return productMapper.toProductResponse(savedProduct);
    }
}
