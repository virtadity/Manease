package com.manease.producer.testcontainers.config;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class KafkaContainerConfig {

    private static final String kafkaFullImageName = "apache/kafka";
    private static final DockerImageName kafkaImageName = DockerImageName.parse(kafkaFullImageName);

    @Container
    @ServiceConnection
    final static KafkaContainer kafka = new KafkaContainer(kafkaImageName);

}
