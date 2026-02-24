package com.virtadity.manease.application.port.in.product;

import com.virtadity.manease.application.model.product.ProductResponse;

import java.util.List;

public interface ProductGetAllInputBoundary {
    List<ProductResponse> execute();
}
