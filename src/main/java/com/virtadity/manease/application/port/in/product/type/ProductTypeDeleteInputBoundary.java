package com.virtadity.manease.application.port.in.product.type;

import java.util.UUID;

public interface ProductTypeDeleteInputBoundary {
    void execute(UUID productTypeId);
}
