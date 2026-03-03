package com.virtadity.manease.infrastructure.database.mapper;

import com.virtadity.manease.domain.model.Producer;
import com.virtadity.manease.infrastructure.database.entity.ProducerEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProducerEntityMapper {
    @Mapping(source = "id", target = "producerId")
    Producer toProducer(ProducerEntity producerEntity);

    @Mapping(source = "producerId", target = "id")
    ProducerEntity toProducerEntity(Producer producer);

    @Mapping(source = "producerId", target = "id")
    void updateFromProducer(@MappingTarget ProducerEntity producerEntity, Producer producer);

    List<Producer> toProducerList(List<ProducerEntity> producerEntityList);
}
