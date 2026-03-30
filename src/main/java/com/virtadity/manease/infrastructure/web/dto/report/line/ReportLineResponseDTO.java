package com.virtadity.manease.infrastructure.web.dto.report.line;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Relation(itemRelation = "report_line", collectionRelation = "report_lines")
public class ReportLineResponseDTO {
    @JsonProperty("purchase_id")
    UUID purchaseId;
    @JsonProperty("delivery_date")
    LocalDateTime deliveryDate;
    @JsonProperty("creation_date")
    LocalDateTime creationDate;
    @JsonProperty("total_purchase_weight")
    BigDecimal totalPurchaseWeight;
    @JsonProperty("total_purchase_cost")
    BigDecimal totalPurchaseCost;
}
