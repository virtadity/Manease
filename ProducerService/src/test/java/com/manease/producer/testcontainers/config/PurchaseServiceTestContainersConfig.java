package com.manease.producer.testcontainers.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.postgresql.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
@ImportTestcontainers({ PostgresContainerConfig.class, KafkaContainerConfig.class })
public class PurchaseServiceTestContainersConfig {

    public static PostgreSQLContainer postgres = PostgresContainerConfig.postgres;
    public static KafkaContainer kafkaContainer = KafkaContainerConfig.kafka;
}
