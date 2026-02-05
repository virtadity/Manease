package com.virtadity.manease.repository;

import com.virtadity.manease.entity.Product;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest
public class ProductRepositoryTests {

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
    ProductRepository productRepository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Test
    void testConnect() {
        assertTrue(postgreSQLContainer.isRunning());
    }

    @Test
    void testCreation() {

        var productType = new ProductType("Красное");
        var savedProductType = productTypeRepository.save(productType);

        var product = new Product();
        product.setName("Яблоко");
        product.setWeight(140.0f);
        product.setProductType(savedProductType);
        productRepository.save(product);
    }

    @Test
    void testUpdate() {
        var productType = new ProductType("Зелёное");
        var savedProductType = productTypeRepository.save(productType);

        var product = new Product();
        product.setName("Груша");
        product.setWeight(200.0f);
        product.setProductType(savedProductType);
        var savedProduct = productRepository.save(product);

        var updatedProductName = "Лимон";
        var updatedProductWeight = 100.0f;
        var updatedProductType = new ProductType("Жёлтое");
        var savedUpdatedProductType = productTypeRepository.save(updatedProductType);

        savedProduct.setName(updatedProductName);
        savedProduct.setWeight(updatedProductWeight);
        savedProduct.setProductType(savedUpdatedProductType);
        var updatedProduct = productRepository.save(savedProduct);

        assertEquals(updatedProductName, updatedProduct.getName());
        assertEquals(updatedProductWeight, updatedProduct.getWeight());
        assertEquals(savedUpdatedProductType, updatedProduct.getProductType());
    }

    @Test
    void testDelete() {
        var productType = new ProductType("Фиолетовый");
        var savedProductType = productTypeRepository.save(productType);

        var product = new Product();
        product.setName("Баклажан");
        product.setWeight(120.0f);
        product.setProductType(savedProductType);

        var savedProduct = productRepository.save(product);
        var id = savedProduct.getId();
        productRepository.deleteById(id);
        assertTrue(productRepository.findById(id).isEmpty());
    }
}
