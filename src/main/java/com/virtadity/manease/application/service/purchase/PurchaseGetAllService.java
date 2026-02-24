package com.virtadity.manease.application.service.purchase;

import com.virtadity.manease.application.mapper.PurchaseMapper;
import com.virtadity.manease.application.model.purchase.PurchaseResponse;
import com.virtadity.manease.application.port.in.purchase.PurchaseGetAllInputBoundary;
import com.virtadity.manease.application.port.out.purchase.PurchaseGetAllOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseGetAllService implements PurchaseGetAllInputBoundary {
    private final PurchaseGetAllOutputBoundary purchaseStorageGetAll;
    private final PurchaseMapper purchaseMapper;

    @Override
    public List<PurchaseResponse> execute() {
        var purchaseList = purchaseStorageGetAll.execute();
        return purchaseMapper.toPurchaseResponseList(purchaseList);
    }
}
