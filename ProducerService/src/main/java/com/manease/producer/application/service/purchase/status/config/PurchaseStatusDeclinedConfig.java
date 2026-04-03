package com.manease.producer.application.service.purchase.status.config;

import com.manease.producer.application.service.purchase.status.fetcher.PurchaseStatusFetcher;
import com.manease.producer.domain.entity.purchase.PurchaseStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class PurchaseStatusDeclinedConfig {

    @Bean
    public PurchaseStatus purchaseStatusDeclined(PurchaseStatusFetcher fetcher) {
        var id = UUID.randomUUID();
        var name = "declined";
        var description = "The status for declined purchase by producer";
        var purchaseStatus = new PurchaseStatus(id, name, description);
        return fetcher.fetchOrCreatePurchaseStatus(purchaseStatus);
    }

}
