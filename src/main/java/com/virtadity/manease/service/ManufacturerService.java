package com.virtadity.manease.service;

import com.virtadity.manease.dto.ManufacturerDTO;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {
    List<ManufacturerDTO> all();
    Optional<ManufacturerDTO> one(Long id);
    ManufacturerDTO store(ManufacturerDTO manufacturerDTO, Optional<Long> id);
    void delete(Long id);
}
