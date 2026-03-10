package com.virtadity.manease.infrastructure.web.rest_controller;

import com.virtadity.manease.application.model.report.ReportResponse;
import com.virtadity.manease.application.port.in.report.ReportInputBoundary;
import com.virtadity.manease.infrastructure.web.dto.report.ReportResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportInputBoundary actionGetReportBetweenDates;
    private final RepresentationModelAssembler<ReportResponse, EntityModel<ReportResponseDTO>> assembler;

    @GetMapping("/report")
    public EntityModel<ReportResponseDTO> betweenDates(
            @RequestParam("from_date") LocalDateTime fromDate,
            @RequestParam("before_date") LocalDateTime toDate
            ) {
        var reportResponse = actionGetReportBetweenDates.execute(fromDate, toDate);
        return assembler.toModel(reportResponse);
    }
}
