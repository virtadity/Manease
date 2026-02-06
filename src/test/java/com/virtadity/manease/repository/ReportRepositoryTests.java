package com.virtadity.manease.repository;

import com.virtadity.manease.entity.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Testcontainers
@SpringBootTest
public class ReportRepositoryTests {

    @Container
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer(
            DockerImageName.parse("postgres:latest"))
            .withDatabaseName("manease")
            .withUsername("manease")
            .withPassword("manease_secret");

    @BeforeAll
    static void beforeAll() {
        postgreSQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        postgreSQLContainer.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Autowired
    SupplyRepository supplyRepository;

    @Autowired
    ManufacturerRepository manufacturerRepository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PurchaseLineRepository purchaseLineRepository;

    @Test
    void testSearchSupplyByDates() {
        var manufacturer = new Manufacturer("ВкусВилл");
        var savedManufacturer = manufacturerRepository.save(manufacturer);

        var supplies = List.of(
                new Supply(
                        manufacturer,
                        "Поставка бананов",
                        Date.valueOf(LocalDate.of(2020, 6, 19)),
                        Date.valueOf(LocalDate.of(2020, 6, 15))
                        ),

                new Supply(
                        manufacturer,
                        "Поставка груш",
                        Date.valueOf(LocalDate.of(2021, 3, 8)),
                        Date.valueOf(LocalDate.of(2021, 3, 5))
                ),

                new Supply(
                        manufacturer,
                        "Поставка апельсинов",
                        Date.valueOf(LocalDate.of(2025, 4, 16)),
                        Date.valueOf(LocalDate.of(2025, 4, 13))
                        )
        );

        var savedSupplies = supplyRepository.saveAll(supplies);

        var fromDate = Date.valueOf(LocalDate.of(2020, 1, 1));
        var toDate = Date.valueOf(LocalDate.of(2022, 1, 1));
        var suppliesBetweenDates = supplyRepository.findSupplyBetweenDates(fromDate, toDate);

        assertEquals(suppliesBetweenDates, savedSupplies.subList(0, 2));
    }

    @Transactional
    @Test
    void testCountTotalPrice() {
        var manufacturer = new Manufacturer("Спутник");
        manufacturer = manufacturerRepository.save(manufacturer);

        var productType = new ProductType("овощь");
        productType = productTypeRepository.save(productType);

        var potato = new Product("Картофель", 100.0f, productType);
        potato.setManufacturers(Set.of(manufacturer));
        potato = productRepository.save(potato);

        var tomato = new Product("Помидор", 200.0f, productType);
        tomato.setManufacturers(Set.of(manufacturer));
        tomato = productRepository.save(tomato);

        var deliveryDate = Date.valueOf(LocalDate.of(2025, 4, 16));
        var creationDate = Date.valueOf(LocalDate.of(2025, 4, 13));

        var supply = new Supply(manufacturer, "Поставка овощей", deliveryDate, creationDate);
        supply = supplyRepository.save(supply);

        var potatoPurchaseLineId = new PurchaseLineCompositeKey(supply.getId(), potato.getId());
        var potatoPurchaseLine = new PurchaseLine(potatoPurchaseLineId, supply, potato, 19.99f, 10);
        potatoPurchaseLine = purchaseLineRepository.save(potatoPurchaseLine);

        var tomatoPurchaseLineId = new PurchaseLineCompositeKey(supply.getId(), tomato.getId());
        var tomatoPurchaseLine = new PurchaseLine(tomatoPurchaseLineId, supply, tomato, 24.99f, 15);
        tomatoPurchaseLine = purchaseLineRepository.save(tomatoPurchaseLine);

        var actualCost = purchaseLineRepository.countTotalPriceOfSupply(supply.getId());
        var expectedCost = 19.99f * 10 + 24.99f * 15;
        assertEquals(expectedCost, actualCost);
    }

    @Transactional
    @Test
    void testCountTotalWeight() {
        var manufacturer = new Manufacturer("Спутник");
        manufacturer = manufacturerRepository.save(manufacturer);

        var productType = new ProductType("овощь");
        productType = productTypeRepository.save(productType);

        var potato = new Product("Картофель", 100.0f, productType);
        potato.setManufacturers(Set.of(manufacturer));
        potato = productRepository.save(potato);

        var tomato = new Product("Помидор", 200.0f, productType);
        tomato.setManufacturers(Set.of(manufacturer));
        tomato = productRepository.save(tomato);

        var deliveryDate = Date.valueOf(LocalDate.of(2025, 4, 16));
        var creationDate = Date.valueOf(LocalDate.of(2025, 4, 13));

        var supply = new Supply(manufacturer, "Поставка овощей", deliveryDate, creationDate);
        supply = supplyRepository.save(supply);

        var potatoPurchaseLineId = new PurchaseLineCompositeKey(supply.getId(), potato.getId());
        var potatoPurchaseLine = new PurchaseLine(potatoPurchaseLineId, supply, potato, 19.99f, 10);
        potatoPurchaseLine = purchaseLineRepository.save(potatoPurchaseLine);

        var tomatoPurchaseLineId = new PurchaseLineCompositeKey(supply.getId(), tomato.getId());
        var tomatoPurchaseLine = new PurchaseLine(tomatoPurchaseLineId, supply, tomato, 24.99f, 15);
        tomatoPurchaseLine = purchaseLineRepository.save(tomatoPurchaseLine);

        var expectedWeight = 100.0f * 10 + 200.0f * 15;
        var actualWeight = purchaseLineRepository.countTotalWeightOfSupply(supply.getId());
        assertEquals(expectedWeight, actualWeight);
    }
}
