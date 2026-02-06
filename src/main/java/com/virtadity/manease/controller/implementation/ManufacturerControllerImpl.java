package com.virtadity.manease.controller.implementation;

import com.virtadity.manease.controller.ManufacturerController;
import com.virtadity.manease.dto.ManufacturerDTO;
import com.virtadity.manease.service.ManufacturerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manufacturers")
public class ManufacturerControllerImpl implements ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerControllerImpl(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/")
    @Override
    public List<ManufacturerDTO> all() {
        return manufacturerService.all();
    }

    @GetMapping("/{id}")
    @Override
    public Optional<ManufacturerDTO> one(@PathVariable Long id) {
        return manufacturerService.one(id);
    }

    @PostMapping("/{id}")
    @Override
    public ManufacturerDTO create(ManufacturerDTO manufacturerDTO) {
        return manufacturerService.store(manufacturerDTO, Optional.empty());
    }

    @PutMapping("/{id}")
    @Override
    public ManufacturerDTO update(@PathVariable Long id, ManufacturerDTO manufacturerDTO) {
        return manufacturerService.store(manufacturerDTO, Optional.of(id));
    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable Long id) {
        manufacturerService.delete(id);
    }
}
