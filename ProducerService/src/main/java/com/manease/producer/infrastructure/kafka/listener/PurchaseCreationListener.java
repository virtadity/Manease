package com.manease.producer.infrastructure.kafka.listener;

import com.manease.producer.application.port.in.purchase.actions.PurchaseRegisterInputBoundary;
import com.manease.producer.infrastructure.kafka.dto.PurchaseCreationKafkaDTO;
import com.manease.producer.infrastructure.kafka.mapper.PurchaseKafkaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PurchaseCreationListener {

    private final PurchaseKafkaMapper purchaseKafkaMapper;
    private final PurchaseRegisterInputBoundary purchaseRegister;

    @KafkaListener(topics = "purchaseCreation", groupId = "producerService")
    public void listener(PurchaseCreationKafkaDTO purchaseDTO) {
        var purchaseRequest = purchaseKafkaMapper.toPurchaseRequest(purchaseDTO);
        purchaseRegister.execute(purchaseRequest);
    }

}
