package com.virtadity.manease.application.service.product;

import com.virtadity.manease.application.port.in.product.ProductDeleteInputBoundary;
import com.virtadity.manease.application.port.out.product.ProductDeleteOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductDeleteService implements ProductDeleteInputBoundary {

    private final ProductDeleteOutputBoundary productStorageDelete;

    @Override
    public void execute(UUID productId) {
        productStorageDelete.execute(productId);
    }
}
