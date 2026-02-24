package com.virtadity.manease.application.port.in.product_type;

import java.util.UUID;

public interface ProductTypeDeleteInputBoundary {
    void execute(UUID productTypeId);
}
