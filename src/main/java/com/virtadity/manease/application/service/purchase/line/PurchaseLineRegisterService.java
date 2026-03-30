package com.virtadity.manease.application.service.purchase.line;

import com.virtadity.manease.application.exception.ProductDoesNotExistException;
import com.virtadity.manease.application.exception.PurchaseDoesNotExistException;
import com.virtadity.manease.application.mapper.PurchaseLineMapper;
import com.virtadity.manease.application.model.purchase.line.PurchaseLineRequest;
import com.virtadity.manease.application.model.purchase.line.PurchaseLineResponse;
import com.virtadity.manease.application.port.in.purchase.line.PurchaseLineRegisterInputBoundary;
import com.virtadity.manease.application.port.out.product.ProductGetOneOutputBoundary;
import com.virtadity.manease.application.port.out.purchase.PurchaseGetOneOutputBoundary;
import com.virtadity.manease.application.port.out.purchase.line.PurchaseLineCreateOutputBoundary;
import com.virtadity.manease.application.port.out.purchase.line.PurchaseLineGetAllOfPurchaseOutputBoundary;
import com.virtadity.manease.domain.service.purchase.validation.ValidateProducerOfProductAndPurchase;
import com.virtadity.manease.domain.service.purchaseline.validation.ValidateProductInPurchaseLines;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PurchaseLineRegisterService implements PurchaseLineRegisterInputBoundary {
    private final PurchaseLineCreateOutputBoundary purchaseLineStorageRegister;
    private final PurchaseLineGetAllOfPurchaseOutputBoundary purchaseLineGetAllOfPurchase;
    private final ProductGetOneOutputBoundary productStorage;
    private final PurchaseGetOneOutputBoundary purchaseStorage;
    private final PurchaseLineMapper purchaseLineMapper;

    @Override
    public PurchaseLineResponse execute(PurchaseLineRequest purchaseLineRequest) {
        var purchaseLine = purchaseLineMapper.toPurchaseLine(purchaseLineRequest);

        var product = productStorage
                .getOne(purchaseLine.productId())
                .orElseThrow(() -> ProductDoesNotExistException.withProductId(purchaseLine.productId()));

        var purchase = purchaseStorage
                .getOne(purchaseLine.purchaseId())
                .orElseThrow(() -> PurchaseDoesNotExistException.withId(purchaseLine.purchaseId()));

        var purchaseList = purchaseLineGetAllOfPurchase.getAllOfPurchase(purchase.purchaseId());

        ValidateProducerOfProductAndPurchase.execute(purchase, product);
        ValidateProductInPurchaseLines.execute(purchaseList, product);

        var savedPurchaseLine = purchaseLineStorageRegister.create(purchaseLine);
        return purchaseLineMapper.toPurchaseLineResponse(savedPurchaseLine);
    }
}
