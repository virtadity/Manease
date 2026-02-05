package com.virtadity.manease.dto;

public record PurchaseLineDTO(
        Long supplyId,
        Long productId,
        Float price,
        Integer quantity
) {
}
