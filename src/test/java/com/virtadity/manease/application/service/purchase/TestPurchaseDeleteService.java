package com.virtadity.manease.application.service.purchase;

import com.virtadity.manease.application.port.out.purchase.PurchaseDeleteOutputBoundary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestPurchaseDeleteService {
    @Mock
    private PurchaseDeleteOutputBoundary purchaseDeleteStorage;

    @InjectMocks
    private PurchaseDeleteService purchaseDeleteService;

    @Test
    void testPurchaseDelete() {
        var purchaseId = UUID.randomUUID();
        purchaseDeleteService.execute(purchaseId);
        verify(purchaseDeleteStorage, Mockito.times(1)).delete(purchaseId);
    }

}
