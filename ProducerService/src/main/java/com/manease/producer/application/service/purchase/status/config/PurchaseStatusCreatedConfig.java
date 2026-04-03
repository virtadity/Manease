package com.manease.producer.application.service.purchase.status.config;

import com.manease.producer.application.service.purchase.status.fetcher.PurchaseStatusFetcher;
import com.manease.producer.domain.entity.purchase.PurchaseStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class PurchaseStatusCreatedConfig {

    @Bean
    public PurchaseStatus PurchaseStatusCreated(PurchaseStatusFetcher fetcher) {
        var id = UUID.randomUUID();
        var name = "created";
        var description = "Initial status assigned to all new purchases";
        var purchaseStatus = new PurchaseStatus(id, name, description);
        return fetcher.fetchOrCreatePurchaseStatus(purchaseStatus);
    }

}
