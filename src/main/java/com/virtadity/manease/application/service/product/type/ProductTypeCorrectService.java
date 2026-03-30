package com.virtadity.manease.application.service.product.type;

import com.virtadity.manease.application.mapper.ProductTypeMapper;
import com.virtadity.manease.application.model.product.type.ProductTypeRequest;
import com.virtadity.manease.application.model.product.type.ProductTypeResponse;
import com.virtadity.manease.application.port.in.product.type.ProductTypeCorrectInputBoundary;
import com.virtadity.manease.application.port.out.product.type.ProductTypeCorrectOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductTypeCorrectService implements ProductTypeCorrectInputBoundary {

    private final ProductTypeCorrectOutputBoundary productTypeStorageCorrect;
    private final ProductTypeMapper productTypeMapper;

    @Override
    public ProductTypeResponse execute(UUID productTypeId, ProductTypeRequest productTypeRequest) {
        var productType = productTypeMapper.toProductType(productTypeRequest);
        var correctedProductType = productTypeStorageCorrect.correct(productTypeId, productType);
        return productTypeMapper.toProductTypeResponse(correctedProductType);
    }
}
