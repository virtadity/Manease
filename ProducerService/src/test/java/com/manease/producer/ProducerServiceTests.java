package com.manease.producer;

import com.manease.producer.testcontainers.config.PurchaseServiceTestContainersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@Import(PurchaseServiceTestContainersConfig.class)
@ActiveProfiles("test")
public class ProducerServiceTests {
    @Test
    void contextLoads() {

    }
}
