package com.virtadity.manease.controller.implementation;

import com.virtadity.manease.controller.SupplyController;
import com.virtadity.manease.dto.SupplyDTO;
import com.virtadity.manease.service.SupplyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/supplies")
public class SupplyControllerImpl implements SupplyController {

    private final SupplyService supplyService;

    public SupplyControllerImpl(SupplyService supplyService) {
        this.supplyService = supplyService;
    }

    @GetMapping("/")
    @Override
    public List<SupplyDTO> all() {
        return this.supplyService.all();
    }

    @GetMapping("/{id}")
    @Override
    public Optional<SupplyDTO> one(@PathVariable Long id) {
        return this.supplyService.one(id);
    }

    @PostMapping("/")
    @Override
    public SupplyDTO create(SupplyDTO supplyDTO) {
        return this.supplyService.store(supplyDTO, Optional.empty());
    }

    @PutMapping("/{id}")
    @Override
    public SupplyDTO update(@PathVariable Long id, SupplyDTO supplyDTO) {
        return this.supplyService.store(supplyDTO, Optional.of(id));
    }

    @GetMapping("/between-dates")
    @Override
    public List<SupplyDTO> suppliesBetweenDates(@RequestParam String fromDate, @RequestParam String toDate) {
        return this.supplyService.getSuppliesBetweenDates(fromDate, toDate);
    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable Long id) {
        this.supplyService.delete(id);
    }
}
