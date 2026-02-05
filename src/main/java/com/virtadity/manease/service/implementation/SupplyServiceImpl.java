package com.virtadity.manease.service.implementation;

import com.virtadity.manease.dto.SupplyDTO;
import com.virtadity.manease.entity.Supply;
import com.virtadity.manease.repository.ManufacturerRepository;
import com.virtadity.manease.repository.SupplyRepository;
import com.virtadity.manease.service.SupplyService;
import com.virtadity.manease.service.exception.ManufacturerNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SupplyServiceImpl implements SupplyService {

    private final SupplyRepository supplyRepository;
    private final ManufacturerRepository manufacturerRepository;

    public SupplyServiceImpl(SupplyRepository supplyRepository, ManufacturerRepository manufacturerRepository) {
        this.supplyRepository = supplyRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<SupplyDTO> all() {
        return supplyRepository.findAll().stream().map(this::transformToDTO).toList();
    }

    @Override
    public Optional<SupplyDTO> one(Long id) {
        return supplyRepository.findById(id).map(this::transformToDTO);
    }

    @Override
    public SupplyDTO store(SupplyDTO supplyDTO, Optional<Long> id) {
        var supply = transformToSupply(supplyDTO, id);
        var updatedSupply = supplyRepository.save(supply);
        return transformToDTO(updatedSupply);
    }

    @Override
    public void delete(Long id) {
        supplyRepository.deleteById(id);
    }

    private SupplyDTO transformToDTO(Supply supply) {
        return new SupplyDTO(
                supply.getId(),
                supply.getManufacturer().getId(),
                supply.getDescription(),
                supply.getDeliveryDate().toLocalDate(),
                supply.getCreationDate().toLocalDate()
        );
    }

    private Supply transformToSupply(SupplyDTO supplyDTO, Optional<Long> id) {
        var supply = new Supply();

        var manufacturerId = supplyDTO.manufacturerId();
        var manufacturer = this.manufacturerRepository
                .findById(manufacturerId)
                .orElseThrow(() -> ManufacturerNotFoundException.withId(manufacturerId));

        var deliveryDate = Date.valueOf(supplyDTO.deliveryDate());
        var creationDate = Date.valueOf(supplyDTO.creationDate());

        id.ifPresent(supply::setId);
        supply.setManufacturer(manufacturer);
        supply.setDescription(supplyDTO.description());
        supply.setDeliveryDate(deliveryDate);
        supply.setCreationDate(creationDate);

        return supply;
    }
}
