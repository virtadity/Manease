package com.virtadity.manease.application.port.in.product;

import com.virtadity.manease.application.model.product.ProductRequest;
import com.virtadity.manease.application.model.product.ProductResponse;

public interface ProductRegisterInputBoundary {
    ProductResponse execute(ProductRequest productRequest);
}
