package com.virtadity.manease.application.port.in.product;

import java.util.UUID;

public interface ProductDeleteInputBoundary {
    void execute(UUID productId);
}
