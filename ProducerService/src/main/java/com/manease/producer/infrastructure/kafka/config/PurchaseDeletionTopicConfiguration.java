package com.manease.producer.infrastructure.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class PurchaseDeletionTopicConfiguration {

    @Bean
    public NewTopic purchaseDeletionTopic() {
        return TopicBuilder
                .name("purchaseDeletion")
                .partitions(10)
                .compact()
                .build();
    }

}
