package com.virtadity.manease.infrastructure.web.dto.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ReportResponseDTO {
    @JsonProperty("after_date")
    private LocalDateTime afterDate;
    @JsonProperty("before_date")
    private LocalDateTime beforeDate;
    @JsonProperty("total_cost")
    private BigDecimal totalCost;
    @JsonProperty("total_weight")
    private BigDecimal totalWeight;
    @JsonProperty("report_lines")
    private List<EntityModel<ReportLineResponseDTO>> reportLines;
}
