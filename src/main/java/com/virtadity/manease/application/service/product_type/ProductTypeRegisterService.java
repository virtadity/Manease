package com.virtadity.manease.application.service.product_type;

import com.virtadity.manease.application.mapper.ProductTypeMapper;
import com.virtadity.manease.application.model.product_type.ProductTypeRequest;
import com.virtadity.manease.application.model.product_type.ProductTypeResponse;
import com.virtadity.manease.application.port.in.product_type.ProductTypeRegisterInputBoundary;
import com.virtadity.manease.application.port.out.product_type.ProductTypeCreateOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductTypeRegisterService implements ProductTypeRegisterInputBoundary {

    private final ProductTypeCreateOutputBoundary productTypeStorageRegister;
    private final ProductTypeMapper productTypeMapper;

    @Override
    public ProductTypeResponse execute(ProductTypeRequest productTypeRequest) {
        var productType = productTypeMapper.toProductType(productTypeRequest);
        var savedProductType = productTypeStorageRegister.execute(productType);
        return productTypeMapper.toProductTypeResponse(savedProductType);
    }
}
