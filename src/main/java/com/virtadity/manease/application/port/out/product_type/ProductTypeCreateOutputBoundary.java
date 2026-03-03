package com.virtadity.manease.application.port.out.product_type;

import com.virtadity.manease.domain.model.ProductType;

public interface ProductTypeCreateOutputBoundary {
    ProductType create(ProductType productType);

}
