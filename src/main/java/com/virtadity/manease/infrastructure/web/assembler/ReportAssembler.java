package com.virtadity.manease.infrastructure.web.assembler;

import com.virtadity.manease.application.model.report.ReportResponse;
import com.virtadity.manease.application.model.report_line.ReportLineResponse;
import com.virtadity.manease.infrastructure.web.dto.report.ReportLineResponseDTO;
import com.virtadity.manease.infrastructure.web.dto.report.ReportResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.ReportDTOMapper;
import com.virtadity.manease.infrastructure.web.rest_controller.ReportController;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RequiredArgsConstructor
@Component
public class ReportAssembler implements RepresentationModelAssembler<ReportResponse, EntityModel<ReportResponseDTO>> {

    private final ReportDTOMapper reportDTOMapper;
    private final RepresentationModelAssembler<ReportLineResponse, EntityModel<ReportLineResponseDTO>>
            reportLineAssembler;

    @Override
    public EntityModel<ReportResponseDTO> toModel(ReportResponse reportResponse) {
        var reportLines = reportResponse.reportLineResponseList().stream().map(reportLineAssembler::toModel).toList();
        var reportResponseDTO = reportDTOMapper.toReportResponseDTO(reportResponse, reportLines);
        return EntityModel.of(reportResponseDTO,
                linkTo(
                        methodOn(ReportController.class)
                                .betweenDates(reportResponse.afterDate(), reportResponse.beforeDate())
                ).withSelfRel());
    }
}
