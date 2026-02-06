package com.virtadity.manease.controller;

import com.virtadity.manease.dto.PurchaseLineDTO;

import java.util.List;
import java.util.Optional;

public interface PurchaseLineController {
    List<PurchaseLineDTO> all();
    Optional<PurchaseLineDTO> one(Long supplyId, Long productId);
    PurchaseLineDTO create(PurchaseLineDTO purchaseLineDTO);
    PurchaseLineDTO update(PurchaseLineDTO purchaseLineDTO);
    void delete(Long supplyId, Long productId);
}
