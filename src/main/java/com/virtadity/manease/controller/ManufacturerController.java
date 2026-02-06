package com.virtadity.manease.controller;

import com.virtadity.manease.dto.ManufacturerDTO;

import java.util.List;
import java.util.Optional;

public interface ManufacturerController {
    List<ManufacturerDTO> all();
    Optional<ManufacturerDTO> one(Long id);
    ManufacturerDTO create(ManufacturerDTO manufacturerDTO);
    ManufacturerDTO update(Long id, ManufacturerDTO manufacturerDTO);
    void delete(Long id);
}
