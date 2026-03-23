package com.virtadity.manease.application.port.in.product_type;

import com.virtadity.manease.application.model.product_type.ProductTypeResponse;

import java.util.List;

public interface ProductTypeGetAllInputBoundary {
    List<ProductTypeResponse> execute();
}
