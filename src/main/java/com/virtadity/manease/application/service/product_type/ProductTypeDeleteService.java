package com.virtadity.manease.application.service.product_type;

import com.virtadity.manease.application.port.in.product.ProductDeleteInputBoundary;
import com.virtadity.manease.application.port.out.product_type.ProductTypeDeleteOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductTypeDeleteService implements ProductDeleteInputBoundary {

    private final ProductTypeDeleteOutputBoundary productStorageDelete;

    @Override
    public void execute(UUID productId) {
        productStorageDelete.execute(productId);
    }
}
