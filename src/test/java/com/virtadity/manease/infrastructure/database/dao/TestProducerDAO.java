package com.virtadity.manease.infrastructure.database.dao;


import com.virtadity.manease.domain.model.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.UUID;

@Import(PersistenceTestSetting.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestProducerDAO {

    @Autowired
    private ProducerDAO producerDAO;

    @Test
    void testCreateProducer() {
        var producer = new Producer(UUID.randomUUID(), "Yahoo");

        var createdProducer = producerDAO.create(producer);

        assertThat(createdProducer).isNotNull();
        assertThat(createdProducer).isEqualTo(producer);
    }

    @Test
    void testGetOneProducer() {
        var producer = new Producer(UUID.randomUUID(), "Apple");
        producerDAO.create(producer);

        var producerById = producerDAO.getOne(producer.producerId());

        assertThat(producerById).isNotNull();
        assertThat(producerById).isEqualTo(Optional.of(producer));
    }

    @Test
    void testGetAllProducers() {
        var producers = producerDAO.getAll();

        assertThat(producers).isNotNull();
        assertThat(producers).isNotEmpty();
    }

    @Test
    void testCorrectProducer() {
        var producer = new Producer(UUID.randomUUID(), "Apple");
        producerDAO.create(producer);

        var producerForUpdate = new Producer(producer.producerId(), "Google");
        var updatedProducer = producerDAO.correct(producer.producerId(), producerForUpdate);

        assertThat(updatedProducer).isNotNull();
        assertThat(updatedProducer).isEqualTo(producerForUpdate);
    }

    @Test
    void testDeleteProducer() {
        var producer = new Producer(UUID.randomUUID(), "Apple");
        producerDAO.create(producer);

        producerDAO.delete(producer.producerId());
        var producerAfterDeletion = producerDAO.getOne(producer.producerId());
        assertThat(producerAfterDeletion).isNotNull();
        assertThat(producerAfterDeletion).isEmpty();
    }
}
