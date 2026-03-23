package com.virtadity.manease.application.port.out.product;

import com.virtadity.manease.domain.model.Product;

import java.util.List;

public interface ProductGetAllOutputBoundary {
    List<Product> getAll();
}
