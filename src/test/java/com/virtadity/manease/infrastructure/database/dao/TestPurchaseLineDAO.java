package com.virtadity.manease.infrastructure.database.dao;

import com.virtadity.manease.AppTestContainersConfig;
import com.virtadity.manease.domain.model.PurchaseLine;
import com.virtadity.manease.infrastructure.database.entity.*;
import com.virtadity.manease.infrastructure.database.mapper.PurchaseLineEntityMapperImpl;
import com.virtadity.manease.infrastructure.database.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceTestSetting.class)
public class TestPurchaseLineDAO {

    @Autowired
    private PurchaseLineDAO purchaseLineDAO;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    private List<PurchaseEntity> purchaseEntityList;
    private List<ProductEntity> productEntityList;

    @BeforeEach
    void setUp() {
        setUpPurchaseEntityList();
        setUpProductEntityList();
    }

    void setUpPurchaseEntityList() {
        purchaseEntityList = purchaseRepository.findAll();
    }

    void setUpProductEntityList() {
        productEntityList = productRepository.findAll();
    }

    @Test
    void testCreatePurchaseLine() {
        var purchaseId = UUID.fromString("450dfe1a-ca54-4cc8-b88c-d83b552768d0");
        var productId = UUID.fromString("5acf035c-d833-4d3f-9fa4-0f66550a962b");
        var purchaseLine = new PurchaseLine(purchaseId, productId, BigDecimal.valueOf(112.0), BigInteger.valueOf(100));

        var createdPurchaseLine = purchaseLineDAO.create(purchaseLine);

        assertThat(createdPurchaseLine)
                .isNotNull()
                .isEqualTo(purchaseLine);
    }

    @Test
    void testCorrectPurchaseLine() {
        var purchaseId = UUID.fromString("450dfe1a-ca54-4cc8-b88c-d83b552768d0");
        var productId = UUID.fromString("5acf035c-d833-4d3f-9fa4-0f66550a962b");
        var purchaseLine = new PurchaseLine(purchaseId, productId, BigDecimal.valueOf(112.0), BigInteger.valueOf(100));

        purchaseLineDAO.create(purchaseLine);

        var correctPurchaseLine = new PurchaseLine(
                purchaseId,
                productId,
                BigDecimal.valueOf(116),
                BigInteger.valueOf(105)
        );

        var correctedPurchaseLine = purchaseLineDAO.correct(purchaseId, productId, correctPurchaseLine);

        assertThat(correctedPurchaseLine)
                .isNotNull()
                .isEqualTo(correctPurchaseLine);
    }

    @Test
    void testGetAllPurchaseLine() {
        var purchaseLineEntityList = purchaseLineDAO.getAll();
        assertThat(purchaseLineEntityList)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void testGetOnePurchaseLine() {
        var purchaseId = UUID.fromString("450dfe1a-ca54-4cc8-b88c-d83b552768d0");
        var productId = UUID.fromString("5acf035c-d833-4d3f-9fa4-0f66550a962b");
        var purchaseLine = new PurchaseLine(purchaseId, productId, BigDecimal.valueOf(112.0), BigInteger.valueOf(100));
        purchaseLineDAO.create(purchaseLine);

        var purchaseLineByIds = purchaseLineDAO.getOne(purchaseId, productId);
        assertThat(purchaseLineByIds)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(Optional.of(purchaseLine));
    }

    @Test
    void testDeletePurchaseLine() {
        var purchaseId = UUID.fromString("450dfe1a-ca54-4cc8-b88c-d83b552768d0");
        var productId = UUID.fromString("5acf035c-d833-4d3f-9fa4-0f66550a962b");
        var purchaseLine = new PurchaseLine(purchaseId, productId, BigDecimal.valueOf(112.0), BigInteger.valueOf(100));
        purchaseLineDAO.create(purchaseLine);

        purchaseLineDAO.delete(purchaseId, productId);
        var purchaseLineByIds = purchaseLineDAO.getOne(purchaseId, productId);
        assertThat(purchaseLineByIds)
                .isNotNull()
                .isEmpty();
    }

    @Test
    void testGetAllPurchaseLineOfPurchase() {
        var purchaseId = UUID.fromString("450dfe1a-ca54-4cc8-b88c-d83b552768d0");
        var purchaseLines = purchaseLineDAO.getAllOfPurchase(purchaseId);

        assertThat(purchaseLines)
                .isNotNull()
                .isNotEmpty();
    }
}
