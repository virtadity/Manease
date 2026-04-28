package com.manease.producer;

import com.manease.producer.testcontainers.config.PurchaseServiceTestContainersConfig;
import org.springframework.boot.SpringApplication;

public class TestManeaseProducerServiceApplication {
    public static void main(String[] args) {
        SpringApplication
                .from(ManeaseProducerApplication::main)
                .with(PurchaseServiceTestContainersConfig.class)
                .run(args);
    }
}
