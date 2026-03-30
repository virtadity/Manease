package com.virtadity.manease.application.service.purchase.line;

import com.virtadity.manease.application.mapper.PurchaseLineMapper;
import com.virtadity.manease.application.model.purchase.line.PurchaseLineResponse;
import com.virtadity.manease.application.port.in.purchase.line.PurchaseLineGetAllInputBoundary;
import com.virtadity.manease.application.port.out.purchase.line.PurchaseLineGetAllOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseLineGetAllService implements PurchaseLineGetAllInputBoundary {
    private final PurchaseLineGetAllOutputBoundary purchaseLineStorageGetAll;
    private final PurchaseLineMapper purchaseLineMapper;

    @Override
    public List<PurchaseLineResponse> execute() {
        var purchaseLineList = purchaseLineStorageGetAll.getAll();
        return purchaseLineMapper.toPurchaseLineResponseList(purchaseLineList);
    }
}
