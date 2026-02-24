package com.virtadity.manease.application.service.product_type;

import com.virtadity.manease.application.mapper.ProductTypeMapper;
import com.virtadity.manease.application.model.product_type.ProductTypeRequest;
import com.virtadity.manease.application.model.product_type.ProductTypeResponse;
import com.virtadity.manease.application.port.in.product_type.ProductTypeCorrectInputBoundary;
import com.virtadity.manease.application.port.out.product_type.ProductTypeCorrectOutputBoundary;
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
        var correctedProductType = productTypeStorageCorrect.execute(productTypeId, productType);
        return productTypeMapper.toProductTypeResponse(correctedProductType);
    }
}
