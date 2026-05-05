package com.virtadity.manease.application.service.purchase.line;

import com.virtadity.manease.application.port.out.purchase.line.PurchaseLineDeleteOutputBoundary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestPurchaseLineDeleteService {

    @Mock
    private PurchaseLineDeleteOutputBoundary purchaseLineStorage;

    @InjectMocks
    private PurchaseLineDeleteService purchaseLineDeleteService;

    @Test
    void testPurchaseDelete() {
        var purchaseId = UUID.randomUUID();
        var productId = UUID.randomUUID();
        purchaseLineDeleteService.execute(purchaseId, productId);
        verify(purchaseLineStorage, Mockito.times(1)).delete(purchaseId, productId);
    }

}
