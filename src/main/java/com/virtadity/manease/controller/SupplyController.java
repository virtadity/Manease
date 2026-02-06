package com.virtadity.manease.controller;

import com.virtadity.manease.dto.SupplyDTO;

import java.util.List;
import java.util.Optional;

public interface SupplyController {
    List<SupplyDTO> all();
    Optional<SupplyDTO> one(Long id);
    SupplyDTO create(SupplyDTO supplyDTO);
    SupplyDTO update(Long id, SupplyDTO supplyDTO);
    List<SupplyDTO> suppliesBetweenDates(String fromDate, String toDate);
    void delete(Long id);
}
