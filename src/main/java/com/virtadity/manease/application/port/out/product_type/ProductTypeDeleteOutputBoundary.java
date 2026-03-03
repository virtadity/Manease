package com.virtadity.manease.application.port.out.product_type;

import java.util.UUID;

public interface ProductTypeDeleteOutputBoundary {
    void delete(UUID productTypeId);
}
