package com.virtadity.manease.application.service.purchase.line;

import com.virtadity.manease.application.mapper.PurchaseLineMapper;
import com.virtadity.manease.application.model.purchase.line.PurchaseLineRequest;
import com.virtadity.manease.application.model.purchase.line.PurchaseLineResponse;
import com.virtadity.manease.application.port.out.product.ProductGetOneOutputBoundary;
import com.virtadity.manease.application.port.out.purchase.PurchaseGetOneOutputBoundary;
import com.virtadity.manease.application.port.out.purchase.line.PurchaseLineCreateOutputBoundary;
import com.virtadity.manease.application.port.out.purchase.line.PurchaseLineGetAllOfPurchaseOutputBoundary;
import com.virtadity.manease.domain.model.Product;
import com.virtadity.manease.domain.model.Purchase;
import com.virtadity.manease.domain.model.PurchaseLine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestPurchaseLineRegisterService {

    @Mock
    private PurchaseLineMapper purchaseLineMapper;

    @Mock
    private PurchaseLineCreateOutputBoundary purchaseLineCreateStorage;

    @Mock
    private ProductGetOneOutputBoundary productGetOneStorage;

    @Mock
    private PurchaseGetOneOutputBoundary purchaseGetOneStorage;

    @Mock
    private PurchaseLineGetAllOfPurchaseOutputBoundary purchaseLineGetAllOfPurchaseStorage;

    @InjectMocks
    private PurchaseLineRegisterService purchaseLineRegisterService;

    @Test
    void testPurchaseLineRegister() {
        var purchaseId = UUID.randomUUID();
        var productId = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var producerTypeId = UUID.randomUUID();
        var price = BigDecimal.valueOf(100.0);
        var quantity = BigInteger.valueOf(100);

        var purchaseLineRequest = new PurchaseLineRequest(purchaseId, productId, price, quantity);
        var purchaseLine = new PurchaseLine(purchaseId, productId, price, quantity);
        var purchaseLineResponse = new PurchaseLineResponse(purchaseId, productId, price, quantity);

        var purchase = new Purchase(
                purchaseId,
                "Test description",
                LocalDateTime.now(),
                LocalDateTime.now(),
                producerId
        );

        var product = new Product(
                productId,
                "Test product",
                BigDecimal.valueOf(100.0),
                producerId,
                producerTypeId
        );

        when(purchaseLineMapper.toPurchaseLine(purchaseLineRequest)).thenReturn(purchaseLine);
        when(purchaseLineMapper.toPurchaseLineResponse(purchaseLine)).thenReturn(purchaseLineResponse);
        when(productGetOneStorage.getOne(productId)).thenReturn(Optional.of(product));
        when(purchaseGetOneStorage.getOne(purchaseId)).thenReturn(Optional.of(purchase));
        when(purchaseLineCreateStorage.create(purchaseLine)).thenReturn(purchaseLine);
        when(purchaseLineGetAllOfPurchaseStorage.getAllOfPurchase(purchaseId)).thenReturn(List.of());

        var actualPurchaseLineResponse = purchaseLineRegisterService.execute(purchaseLineRequest);
        assertThat(actualPurchaseLineResponse)
                .isNotNull()
                .isEqualTo(purchaseLineResponse);
    }
}
