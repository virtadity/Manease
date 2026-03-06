package com.virtadity.manease.application.service.purchase;

import com.virtadity.manease.application.mapper.PurchaseMapper;
import com.virtadity.manease.application.model.purchase.PurchaseResponse;
import com.virtadity.manease.application.port.out.purchase.PurchaseGetAllOutputBoundary;
import com.virtadity.manease.domain.model.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestPurchaseGetAllService {
    @Mock
    private PurchaseMapper purchaseMapper;

    @Mock
    private PurchaseGetAllOutputBoundary purchaseGetAllStorage;

    @InjectMocks
    private PurchaseGetAllService purchaseGetAllService;

    private List<UUID> purchaseIdList = List.of(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
    );

    private List<String> purchaseDescriptionList = List.of(
            "Test purchase 1",
            "Test purchase 2",
            "Test purchase 3",
            "Test purchase 4",
            "Test purchase 5"
    );

    private List<LocalDateTime> purchaseCreationDateList = List.of(
            LocalDateTime.now(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            LocalDateTime.now()
    );

    private List<LocalDateTime> purchaseDeliveryDateList = List.of(
            LocalDateTime.now(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            LocalDateTime.now()
    );

    private List<UUID> producerIdList = List.of(
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID()
    );

    private List<Purchase> purchaseList;

    private void setUpPurchaseList() {
        purchaseList = new ArrayList<>();
        for (int index = 0; index < purchaseIdList.size(); index++) {
            var purchaseId = purchaseIdList.get(index);
            var description = purchaseDescriptionList.get(index);
            var creationDate = purchaseCreationDateList.get(index);
            var deliveryDate = purchaseDeliveryDateList.get(index);
            var producerId = producerIdList.get(index);
            var purchase = new Purchase(
                    purchaseId,
                    description,
                    creationDate,
                    deliveryDate,
                    producerId
            );
            purchaseList.add(purchase);
        }
    }

    private List<PurchaseResponse> purchaseResponseList;

    private void setUpPurchaseResponseList() {
        purchaseResponseList = new ArrayList<>();
        for (int index = 0; index < purchaseIdList.size(); index++) {
            var purchaseId = purchaseIdList.get(index);
            var description = purchaseDescriptionList.get(index);
            var creationDate = purchaseCreationDateList.get(index);
            var deliveryDate = purchaseDeliveryDateList.get(index);
            var producerId = producerIdList.get(index);
            var purchaseResponse = new PurchaseResponse(
                    purchaseId,
                    description,
                    creationDate,
                    deliveryDate,
                    producerId
            );
            purchaseResponseList.add(purchaseResponse);
        }
    }

    @BeforeEach
    void setUp() {
        setUpPurchaseList();
        setUpPurchaseResponseList();
    }

    @Test
    void testPurchaseGetAll() {
        when(purchaseMapper.toPurchaseResponseList(purchaseList)).thenReturn(purchaseResponseList);
        when(purchaseGetAllStorage.getAll()).thenReturn(purchaseList);

        var actualPurchaseResponseList = purchaseGetAllService.execute();
        assertThat(actualPurchaseResponseList)
                .isNotNull()
                .isEqualTo(purchaseResponseList);
    }
}
