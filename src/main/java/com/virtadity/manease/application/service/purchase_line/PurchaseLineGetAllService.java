package com.virtadity.manease.application.service.purchase_line;

import com.virtadity.manease.application.mapper.PurchaseLineMapper;
import com.virtadity.manease.application.model.purchase_line.PurchaseLineResponse;
import com.virtadity.manease.application.port.in.purchase_line.PurchaseLineGetAllInputBoundary;
import com.virtadity.manease.application.port.out.purchase_line.PurchaseLineGetAllOutputBoundary;
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
        var purchaseLineList = purchaseLineStorageGetAll.execute();
        return purchaseLineMapper.toPurchaseLineResponseList(purchaseLineList);
    }
}
