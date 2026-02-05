package com.virtadity.manease.repository;


import com.virtadity.manease.entity.Manufacturer;
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
public class ManufacturerRepositoryTests {

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
    ManufacturerRepository manufacturerRepository;

    @Test
    void testConnect() {
        assertTrue(postgreSQLContainer.isRunning());
    }

    @Test
    void testCreation() {
        var manufacturer = new Manufacturer("Магнит");
        var savedManufacturer = manufacturerRepository.save(manufacturer);
    }

    @Test
    void testUpdate() {
        var manufacturer = new Manufacturer("Лента");
        var savedManufacturer = manufacturerRepository.save(manufacturer);
        var updatedManufacturerName = "Пятёрочка";
        savedManufacturer.setName(updatedManufacturerName);
        var updatedManufacturer = manufacturerRepository.save(savedManufacturer);
        assertEquals(updatedManufacturerName, updatedManufacturer.getName());
    }

    @Test
    void testDelete() {
        var manufacturer = new Manufacturer("Метро");
        var savedManufacturer = manufacturerRepository.save(manufacturer);
        var id = savedManufacturer.getId();
        manufacturerRepository.deleteById(id);
        assertTrue(manufacturerRepository.findById(id).isEmpty());
    }
}
