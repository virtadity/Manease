package com.manease.producer.testcontainers.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class PostgresContainerConfig {

    private static final String postgresImageFullName = "postgres:latest";
    private static final DockerImageName postgresImageName = DockerImageName.parse(postgresImageFullName);

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer(postgresImageName);

    @DynamicPropertySource
    static void postgresSetProperties(DynamicPropertyRegistry registry) {
        setDatasourceProperties(registry);
        setLiquibaseProperties(registry);
    }

    static void setDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    static void setLiquibaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.liquibase.url", postgres::getJdbcUrl);
        registry.add("spring.liquibase.user", postgres::getUsername);
        registry.add("spring.liquibase.password", postgres::getPassword);
    }
}
