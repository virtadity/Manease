package com.virtadity.manease.service;

import com.virtadity.manease.dto.SupplyDTO;

import java.util.List;
import java.util.Optional;

public interface SupplyService {
    List<SupplyDTO> all();
    Optional<SupplyDTO> one(Long id);
    SupplyDTO store(SupplyDTO supplyDTO, Optional<Long> id);
    List<SupplyDTO> getSuppliesBetweenDates();
    void delete(Long id);
}
