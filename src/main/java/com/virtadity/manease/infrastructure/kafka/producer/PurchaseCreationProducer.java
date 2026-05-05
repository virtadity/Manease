package com.virtadity.manease.infrastructure.kafka.producer;

import com.virtadity.manease.application.service.purchase.event.PurchaseRegisteredEvent;
import com.virtadity.manease.infrastructure.kafka.mapper.PurchaseKafkaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class PurchaseCreationProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final PurchaseKafkaMapper purchaseKafkaMapper;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void dispatchPurchaseRegistered(PurchaseRegisteredEvent purchaseRegisteredEvent) {
        var purchaseDTO = purchaseKafkaMapper.toPurchaseCreationKafkaDTO(purchaseRegisteredEvent.purchase());
        kafkaTemplate.send("purchaseCreation", purchaseDTO.id().toString(), purchaseDTO);
    }
}
