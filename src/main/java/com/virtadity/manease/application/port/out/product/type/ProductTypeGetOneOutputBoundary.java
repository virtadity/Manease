package com.virtadity.manease.application.port.out.product.type;


import com.virtadity.manease.domain.model.ProductType;

import java.util.Optional;
import java.util.UUID;

public interface ProductTypeGetOneOutputBoundary {
    Optional<ProductType> getOne(UUID productTypeId);
}
