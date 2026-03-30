package com.virtadity.manease.application.port.in.product.type;

import com.virtadity.manease.application.model.product.type.ProductTypeRequest;
import com.virtadity.manease.application.model.product.type.ProductTypeResponse;

import java.util.UUID;

public interface ProductTypeCorrectInputBoundary {
    ProductTypeResponse execute(UUID productTypeId, ProductTypeRequest productTypeRequest);
}
