package com.virtadity.manease.infrastructure.database.dao;

import com.virtadity.manease.PersistenceTestSetting;
import com.virtadity.manease.domain.model.Purchase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Import(PersistenceTestSetting.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestPurchaseDAO {

    @Autowired
    private PurchaseDAO purchaseDAO;

    private final UUID producerId = UUID.fromString("6d26ba51-2537-470c-8f99-384d20d138e6");

    @Test
    void testCreatePurchase() {
        var purchase = createPurchase("Закупка белых яблок из магнита", LocalDateTime.now(), LocalDateTime.now());
        var createdPurchase = purchaseDAO.create(purchase);
        assertThat(createdPurchase).isNotNull().isEqualTo(purchase);
    }

    private Purchase createPurchase(String description, LocalDateTime creationDate, LocalDateTime deliveryDate) {
        var purchase = new Purchase(UUID.randomUUID(), description, creationDate, deliveryDate, producerId);
        return purchaseDAO.create(purchase);
    }

    @Test
    void testGetOnePurchase() {
        var description = "Закупка яблок из Москвы";
        var purchase = createPurchase(description, LocalDateTime.now(), LocalDateTime.now());
        var purchaseById = purchaseDAO.getOne( purchase.purchaseId());

        assertThat(purchaseById).isNotNull().isEqualTo(Optional.of(purchase));
    }

    @Test
    void testGetAllPurchase() {
        var purchaseList = purchaseDAO.getAll();

        assertThat(purchaseList)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void testCorrectPurchase() {
        var description = "Закупка яблок из Москвы";
        var creationDate = LocalDateTime.now();
        var deliveryDate = LocalDateTime.now();
        var purchase = createPurchase(description, creationDate, deliveryDate);
        var purchaseId = purchase.purchaseId();

        description = "Закупка яблок из Петербурга";
        purchase = new Purchase(purchaseId, description, creationDate, deliveryDate, producerId);
        var correctedPurchase = purchaseDAO.correct(purchaseId, purchase);

        assertThat(correctedPurchase)
                .isNotNull()
                .isEqualTo(purchase);
    }

    @Test
    void testDeletePurchase() {
        var description = "Закупка яблок из Москвы";
        var creationDate = LocalDateTime.now();
        var deliveryDate = LocalDateTime.now();
        var purchase = createPurchase(description, creationDate, deliveryDate);
        var purchaseId = purchase.purchaseId();

        purchaseDAO.delete(purchaseId);
        var deletedPurchase = purchaseDAO.getOne(purchaseId);

        assertThat(deletedPurchase)
                .isNotNull()
                .isEmpty();
    }
}
