package com.virtadity.manease.application.port.in.product_type;

import com.virtadity.manease.application.model.product_type.ProductTypeRequest;
import com.virtadity.manease.application.model.product_type.ProductTypeResponse;

import java.util.UUID;

public interface ProductTypeCorrectInputBoundary {
    ProductTypeResponse execute(UUID productTypeId, ProductTypeRequest productTypeRequest);
}
