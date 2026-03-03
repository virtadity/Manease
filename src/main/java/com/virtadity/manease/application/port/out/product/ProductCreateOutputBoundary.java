package com.virtadity.manease.application.port.out.product;

import com.virtadity.manease.domain.model.Product;

public interface ProductCreateOutputBoundary {
    Product create(Product product);
}
