package com.manease.producer.infrastructure.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class PurchaseCreationTopicConfiguration {

    @Bean
    public NewTopic purchaseCreation() {
        return TopicBuilder
                .name("purchaseCreation")
                .partitions(10)
                .compact()
                .build();
    }
}
