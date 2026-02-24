package com.virtadity.manease.application.port.in.product_type;

import com.virtadity.manease.application.model.product_type.ProductTypeRequest;
import com.virtadity.manease.application.model.product_type.ProductTypeResponse;

public interface ProductTypeRegisterInputBoundary {
    ProductTypeResponse execute(ProductTypeRequest producerTypeRequest);
}
