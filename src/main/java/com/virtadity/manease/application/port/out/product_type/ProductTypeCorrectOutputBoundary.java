package com.virtadity.manease.application.port.out.product_type;

import com.virtadity.manease.domain.model.ProductType;

import java.util.UUID;

public interface ProductTypeCorrectOutputBoundary {
    ProductType correct(UUID productTypeId, ProductType productType);
}
