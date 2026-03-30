package com.virtadity.manease.application.service.product.type;

import com.virtadity.manease.application.port.in.product.type.ProductTypeDeleteInputBoundary;
import com.virtadity.manease.application.port.out.product.type.ProductTypeDeleteOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductTypeDeleteService implements ProductTypeDeleteInputBoundary {

    private final ProductTypeDeleteOutputBoundary productStorageDelete;

    @Override
    public void execute(UUID productId) {
        productStorageDelete.delete(productId);
    }
}
