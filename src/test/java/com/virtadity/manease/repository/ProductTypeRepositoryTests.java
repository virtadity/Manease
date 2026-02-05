package com.virtadity.manease.repository;

import com.virtadity.manease.entity.ProductType;
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

import static org.junit.jupiter.api.Assertions.*;


@Testcontainers
@SpringBootTest
public class ProductTypeRepositoryTests {

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
    ProductTypeRepository productTypeRepository;

    @Test
    void testConnect() {
        assertTrue(postgreSQLContainer.isRunning());
    }

    @Test
    void testCreation() {
        var productType = new ProductType();
        productType.setName("Зелённые");
        var savedProductType = productTypeRepository.save(productType);
    }

    @Test
    void testUpdate() {
        var productType = new ProductType();
        productType.setName("Желтые");
        var savedProductType = productTypeRepository.save(productType);

        var updatedProductName = "Красные";
        savedProductType.setName(updatedProductName);
        var updatedProductType = productTypeRepository.save(savedProductType);

        assertEquals(updatedProductName, updatedProductType.getName());
    }

    @Test
    void testDelete() {
        var productType = new ProductType();
        productType.setName("Фиолетовые");
        var savedProductType = productTypeRepository.save(productType);

        var id = savedProductType.getId();
        productTypeRepository.deleteById(id);
        assertTrue(productTypeRepository.findById(id).isEmpty());
    }
}
