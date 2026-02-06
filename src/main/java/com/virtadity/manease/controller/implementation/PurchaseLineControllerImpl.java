package com.virtadity.manease.controller.implementation;

import com.virtadity.manease.controller.PurchaseLineController;
import com.virtadity.manease.dto.PurchaseLineDTO;
import com.virtadity.manease.service.PurchaseLineService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/purchase-lines")
public class PurchaseLineControllerImpl implements PurchaseLineController {
    private final PurchaseLineService purchaseLineService;

    public PurchaseLineControllerImpl(PurchaseLineService purchaseLineService) {
        this.purchaseLineService = purchaseLineService;
    }

    @GetMapping("/")
    @Override
    public List<PurchaseLineDTO> all() {
        return this.purchaseLineService.all();
    }

    @GetMapping("/one")
    @Override
    public Optional<PurchaseLineDTO> one(
            @PathParam("supply_id") Long supplyId,
            @PathParam("product_id") Long productId
    ) {
        return this.purchaseLineService.one(supplyId, productId);
    }

    @PostMapping("/")
    @Override
    public PurchaseLineDTO create(PurchaseLineDTO purchaseLineDTO) {
        return this.purchaseLineService.store(purchaseLineDTO);
    }

    @PutMapping("/")
    @Override
    public PurchaseLineDTO update(PurchaseLineDTO purchaseLineDTO) {
        return this.purchaseLineService.store(purchaseLineDTO);
    }

    @DeleteMapping("/")
    @Override
    public void delete(Long supplyId, Long productId) {
        this.purchaseLineService.delete(supplyId, productId);
    }
}
