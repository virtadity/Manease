package com.virtadity.manease.infrastructure.web.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReportResponseDTO {
    private LocalDateTime afterDate;
    private LocalDateTime beforeDate;
    private BigDecimal totalCost;
    private BigDecimal totalWeight;
    private CollectionModel<EntityModel<ReportLineResponseDTO>> reportLines;
}
