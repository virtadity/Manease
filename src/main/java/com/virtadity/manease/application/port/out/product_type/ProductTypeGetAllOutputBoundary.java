package com.virtadity.manease.application.port.out.product_type;

import com.virtadity.manease.domain.model.ProductType;

import java.util.List;

public interface ProductTypeGetAllOutputBoundary {
    List<ProductType> execute();
}
