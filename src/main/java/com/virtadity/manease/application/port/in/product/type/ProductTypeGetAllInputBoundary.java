package com.virtadity.manease.application.port.in.product.type;

import com.virtadity.manease.application.model.product.type.ProductTypeResponse;

import java.util.List;

public interface ProductTypeGetAllInputBoundary {
    List<ProductTypeResponse> execute();
}
