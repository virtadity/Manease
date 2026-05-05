package com.manease.producer.infrastructure.kafka;

import com.manease.producer.application.entity.request.PurchaseRequest;
import com.manease.producer.application.entity.response.PurchaseResponse;
import com.manease.producer.application.port.in.purchase.actions.PurchaseRegisterInputBoundary;
import com.manease.producer.infrastructure.kafka.dto.PurchaseCreationKafkaDTO;
import com.manease.producer.infrastructure.kafka.listener.PurchaseCreationListener;
import com.manease.producer.infrastructure.kafka.mapper.PurchaseKafkaMapper;
import com.manease.producer.testcontainers.config.PurchaseServiceTestContainersConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(properties = "spring.kafka.consumer.auto-offset-reset=earliest")
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Import(PurchaseServiceTestContainersConfig.class)
@Slf4j
public class PurchaseCreationListenerTest {

    @Autowired
    private KafkaTemplate<String, PurchaseCreationKafkaDTO> kafkaTemplate;

    @MockitoSpyBean
    private PurchaseKafkaMapper purchaseKafkaMapper;

    @MockitoSpyBean
    private PurchaseRegisterInputBoundary purchaseRegister;

    @InjectMocks
    private PurchaseCreationListener purchaseCreationListener;

    @BeforeAll
    static void setUp() {

        var bootstrapServers = PurchaseServiceTestContainersConfig.kafkaContainer.getBootstrapServers();

        var properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        try (var admin = AdminClient.create(properties)) {
            admin.createTopics(List.of(
                new NewTopic("purchaseCreation", 1, (short) 1)
            ));
        }
    }

    @Test
    void testPurchaseCreationEvent() {
        var purchaseId = UUID.randomUUID();
        var producerId = UUID.randomUUID();
        var createdStatusId = UUID.randomUUID();
        var purchaseCreationKafkaDTO = new PurchaseCreationKafkaDTO(purchaseId, producerId);
        var purchaseRequest = new PurchaseRequest(purchaseId, producerId);
        var purchaseResponse = new PurchaseResponse(purchaseId, producerId, createdStatusId);

        when(purchaseKafkaMapper.toPurchaseRequest(purchaseCreationKafkaDTO)).thenReturn(purchaseRequest);
        when(purchaseRegister.execute(purchaseRequest)).thenReturn(purchaseResponse);

        kafkaTemplate
                .send("purchaseCreation", purchaseCreationKafkaDTO.id().toString(), purchaseCreationKafkaDTO)
                .thenRun(() -> await()
                        .pollInterval(Duration.ofSeconds(3))
                        .atMost(10, TimeUnit.SECONDS)
                        .untilAsserted(() ->
                                verify(purchaseRegister, times(1)).execute(purchaseRequest)
                        )
                );
    }
}
