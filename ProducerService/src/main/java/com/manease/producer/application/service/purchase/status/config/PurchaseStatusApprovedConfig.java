package com.manease.producer.application.service.purchase.status.config;

import com.manease.producer.application.service.purchase.status.fetcher.PurchaseStatusFetcher;
import com.manease.producer.domain.entity.purchase.PurchaseStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class PurchaseStatusApprovedConfig {

    @Bean
    public PurchaseStatus purchaseStatusApproved(PurchaseStatusFetcher fetcher) {
        var id = UUID.randomUUID();
        var name = "approved";
        var description = "The status for approved purchase by producer";
        var purchaseStatus = new PurchaseStatus(id, name, description);
        return fetcher.fetchOrCreatePurchaseStatus(purchaseStatus);
    }

}
