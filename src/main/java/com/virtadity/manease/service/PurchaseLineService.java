package com.virtadity.manease.service;

import com.virtadity.manease.dto.PurchaseLineDTO;

import java.util.List;
import java.util.Optional;

public interface PurchaseLineService {
    List<PurchaseLineDTO> all();
    Optional<PurchaseLineDTO> one(Long supplyId, Long productId);
    PurchaseLineDTO store(PurchaseLineDTO purchaseLineDTO);
    void delete(Long supplyId, Long productId);
}
