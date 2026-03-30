package com.virtadity.manease.infrastructure.web.controller.report;

import com.virtadity.manease.application.model.report.ReportResponse;
import com.virtadity.manease.application.model.report.line.ReportLineResponse;
import com.virtadity.manease.application.port.in.report.ReportInputBoundary;
import com.virtadity.manease.infrastructure.web.assembler.ReportAssembler;
import com.virtadity.manease.infrastructure.web.dto.report.line.ReportLineResponseDTO;
import com.virtadity.manease.infrastructure.web.dto.report.ReportResponseDTO;
import com.virtadity.manease.infrastructure.web.controller.PurchaseController;
import com.virtadity.manease.infrastructure.web.controller.ReportController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportController.class)
public class TestReportControllerBetweenDates {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReportInputBoundary getReportBetweenDates;

    @MockitoBean
    private ReportAssembler reportAssembler;

    private ReportResponse reportResponse;
    private ReportResponseDTO reportResponseDTO;
    private List<ReportLineResponse> reportLineResponseList = new ArrayList<>();
    private List<EntityModel<ReportLineResponseDTO>> reportLineEntityModelList = new ArrayList<>();
    private BigDecimal totalCost = BigDecimal.ZERO;
    private BigDecimal totalWeight = BigDecimal.ZERO;
    private EntityModel<ReportResponseDTO> reportEntityModel;

    @BeforeEach
    void setUp() {
        totalCost = BigDecimal.ZERO;
        totalWeight = BigDecimal.ZERO;
        setUpReportLineResponseList();
        setUpReportLineCollectionModel();
        setUpReportResponse();
        setUpReportResponseDTO();
        setUpReportEntityModel();
    }

    private void setUpReportResponse() {
        reportResponse = new ReportResponse(
                LocalDateTime.now(),
                LocalDateTime.now(),
                totalCost,
                totalWeight,
                reportLineResponseList
        );
    }

    private void setUpReportResponseDTO() {
        reportResponseDTO = new ReportResponseDTO(
                reportResponse.afterDate(),
                reportResponse.beforeDate(),
                totalCost,
                totalWeight,
                reportLineEntityModelList
        );
    }

    private void setUpReportLineResponseList() {
        setUpReportLineResponseList(10);
    }

    private void setUpReportLineResponseList(int quantity) {
        reportLineResponseList = new ArrayList<>();

        var random = new Random();
        var minimumPrice = 100.0;
        var maximumPrice = 1000.0;
        var minimumWeight = 100.0;
        var maximumWeight = 1000.0;

        totalWeight = BigDecimal.ZERO;
        totalCost = BigDecimal.ZERO;

        for (int index = 0; index < quantity; index++) {
            var purchaseId = UUID.randomUUID();
            var deliveryDate = LocalDateTime.now();
            var creationDate = LocalDateTime.now();

            var weightGeneratedValue = minimumWeight + random.nextDouble() + maximumWeight;
            var weight = BigDecimal.valueOf(weightGeneratedValue);
            totalWeight = totalWeight.add(weight);

            var priceGeneratedValue = minimumPrice + random.nextDouble() + maximumPrice;
            var cost = BigDecimal.valueOf(priceGeneratedValue);
            totalCost = totalCost.add(cost);

            var reportLineDTO = new ReportLineResponse(deliveryDate, creationDate, purchaseId, weight, cost);
            reportLineResponseList.add(reportLineDTO);
        }
    }

    private void setUpReportLineCollectionModel() {
        this.reportLineEntityModelList = reportLineResponseList.stream().map(
                reportLineResponse -> {
                    var purchaseId = reportLineResponse.purchaseId();
                    var deliveryDate = reportLineResponse.deliveryDate();
                    var creationDate = reportLineResponse.creationDate();
                    var weight = reportLineResponse.totalPurchaseWeight();
                    var cost = reportLineResponse.totalPurchaseCost();
                    var reportLineDTO = new ReportLineResponseDTO(purchaseId, deliveryDate, creationDate, weight, cost);
                    var purchaseLink = linkTo(methodOn(PurchaseController.class).one(purchaseId)).withRel("purchase");
                    return EntityModel.of(reportLineDTO, purchaseLink);
                }
        ).toList();
    }

    private void setUpReportEntityModel() {
        reportEntityModel = EntityModel.of(reportResponseDTO);
    }

    @Test
    void testBetweenDates() throws Exception {

        var afterDate = LocalDateTime.now().minusHours(15);
        var beforeDate = LocalDateTime.now();
        reportResponse = new ReportResponse(
                afterDate,
                beforeDate,
                reportResponse.totalCost(),
                reportResponse.totalWeight(),
                reportResponse.reportLineResponseList()
        );
        setUpReportResponseDTO();
        setUpReportEntityModel();

        when(getReportBetweenDates.execute(afterDate, beforeDate)).thenReturn(reportResponse);
        when(reportAssembler.toModel(reportResponse)).thenReturn(reportEntityModel);

        var regex = "0+$";
        var afterDateClipped = afterDate.toString().replaceAll(regex, "");
        var beforeDateClipped = beforeDate.toString().replaceAll(regex, "");

        mockMvc.perform(
                get(
                        "/reports/report?after_date={afterDate}&before_date={beforeDate}",
                        afterDateClipped,
                        beforeDateClipped
                )
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.after_date").value(afterDateClipped))
                .andExpect(jsonPath("$.before_date").value(beforeDateClipped))
                .andExpect(jsonPath("$.total_cost").value(reportResponse.totalCost().toString()))
                .andExpect(jsonPath("$.total_weight").value(reportResponse.totalWeight().toString()))
                .andExpect(jsonPath("$.report_lines[*]._links.purchase").exists())
                .andExpect(jsonPath("$.report_lines[*].delivery_date").exists())
                .andExpect(jsonPath("$.report_lines[*].creation_date").exists())
                .andExpect(jsonPath("$.report_lines[*].total_purchase_weight").exists())
                .andExpect(jsonPath("$.report_lines[*].total_purchase_cost").exists())
                .andExpect(jsonPath("$.report_lines[*].purchase_id").exists());
    }
}
