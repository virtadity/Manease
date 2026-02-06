package com.virtadity.manease.service.implementation;

import com.virtadity.manease.dto.PurchaseLineDTO;
import com.virtadity.manease.entity.PurchaseLine;
import com.virtadity.manease.entity.PurchaseLineCompositeKey;
import com.virtadity.manease.repository.ProductRepository;
import com.virtadity.manease.repository.PurchaseLineRepository;
import com.virtadity.manease.repository.SupplyRepository;
import com.virtadity.manease.service.PurchaseLineService;
import com.virtadity.manease.service.exception.ProductNotFoundException;
import com.virtadity.manease.service.exception.SupplyNotFoundException;
import com.virtadity.manease.service.exception.WrongManufacturerException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseLineServiceImpl implements PurchaseLineService {

    private final PurchaseLineRepository purchaseLineRepository;
    private final SupplyRepository supplyRepository;
    private final ProductRepository productRepository;

    public PurchaseLineServiceImpl(
            PurchaseLineRepository purchaseLineRepository,
            SupplyRepository supplyRepository,
            ProductRepository productRepository
            ) {

        this.purchaseLineRepository = purchaseLineRepository;
        this.supplyRepository = supplyRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public List<PurchaseLineDTO> all() {
        return purchaseLineRepository.findAll().stream().map(this::transformToDTO).toList();
    }

    @Override
    public Optional<PurchaseLineDTO> one(Long supplyId, Long productId) {
        var id = new PurchaseLineCompositeKey(supplyId, productId);
        return purchaseLineRepository.findById(id).map(this::transformToDTO);
    }

    @Transactional
    @Override
    public PurchaseLineDTO store(PurchaseLineDTO purchaseLineDTO) {
        var purchaseLine = transformToPurchaseLine(purchaseLineDTO);
        checkManufacturer(purchaseLine);
        var savedPurchaseLine = purchaseLineRepository.save(purchaseLine);
        return transformToDTO(savedPurchaseLine);
    }

    @Override
    public void delete(Long supplyId, Long productId) {
        var id = new PurchaseLineCompositeKey(supplyId, productId);
        purchaseLineRepository.deleteById(id);
    }

    private PurchaseLineDTO transformToDTO(PurchaseLine purchaseLine) {
        return new PurchaseLineDTO(
                purchaseLine.getId().getSupplyId(),
                purchaseLine.getId().getProductId(),
                purchaseLine.getPrice(),
                purchaseLine.getQuantity()
        );
    }

    private PurchaseLine transformToPurchaseLine(PurchaseLineDTO purchaseLineDTO) {
        var supplyId = purchaseLineDTO.supplyId();
        var supply = supplyRepository
                .findById(supplyId)
                .orElseThrow(() -> SupplyNotFoundException.withId(supplyId));

        var productId = purchaseLineDTO.productId();
        var product = productRepository
                .findById(productId)
                .orElseThrow(() -> ProductNotFoundException.withId(productId));

        var purchaseLine = new PurchaseLine();
        var id = new PurchaseLineCompositeKey(supplyId, productId);
        purchaseLine.setId(id);
        purchaseLine.setSupply(supply);
        purchaseLine.setProduct(product);
        purchaseLine.setPrice(purchaseLineDTO.price());
        purchaseLine.setQuantity(purchaseLine.getQuantity());
        return purchaseLine;
    }

    private void checkManufacturer(PurchaseLine purchaseLine) {
        var supplyManufacturer = purchaseLine.getSupply().getManufacturer();
        var product = purchaseLine.getProduct();
        var productManufacturers = product.getManufacturers();

        if (!productManufacturers.contains(supplyManufacturer)) {
            var manufacturerId = supplyManufacturer.getId();
            var productId = product.getId();
            throw WrongManufacturerException.withId(manufacturerId, productId);
        }
    }
}
