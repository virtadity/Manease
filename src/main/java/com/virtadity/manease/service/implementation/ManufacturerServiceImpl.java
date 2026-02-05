package com.virtadity.manease.service.implementation;

import com.virtadity.manease.dto.ManufacturerDTO;
import com.virtadity.manease.entity.Manufacturer;
import com.virtadity.manease.repository.ManufacturerRepository;
import com.virtadity.manease.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<ManufacturerDTO> all() {
        return manufacturerRepository.findAll().stream().map(this::transformToDTO).toList();
    }

    @Override
    public Optional<ManufacturerDTO> one(Long id) {
        return manufacturerRepository.findById(id).map(this::transformToDTO);
    }

    @Override
    public ManufacturerDTO store(ManufacturerDTO manufacturerDTO, Optional<Long> id) {
        var manufacturer = transformToManufacturer(manufacturerDTO, id);
        var savedManufacturer = this.manufacturerRepository.save(manufacturer);
        return transformToDTO(savedManufacturer);
    }

    @Override
    public void delete(Long id) {
        manufacturerRepository.deleteById(id);
    }

    private ManufacturerDTO transformToDTO(Manufacturer manufacturer) {
        return new ManufacturerDTO(manufacturer.getId(), manufacturer.getName());
    }

    private Manufacturer transformToManufacturer(ManufacturerDTO manufacturerDTO, Optional<Long> id) {
        var manufacturer = new Manufacturer(manufacturerDTO.name());
        id.ifPresent(manufacturer::setId);
        return manufacturer;
    }
}
