package com.virtadity.manease.application.port.in.product.type;

import com.virtadity.manease.application.model.product.type.ProductTypeRequest;
import com.virtadity.manease.application.model.product.type.ProductTypeResponse;

public interface ProductTypeRegisterInputBoundary {
    ProductTypeResponse execute(ProductTypeRequest producerTypeRequest);
}
