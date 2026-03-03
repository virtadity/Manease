package com.virtadity.manease.infrastructure.database.repository;

import com.virtadity.manease.infrastructure.database.entity.ProducerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProducerRepository extends JpaRepository<ProducerEntity, UUID> {
}
