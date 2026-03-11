package com.virtadity.manease.infrastructure.web.assembler;

import com.virtadity.manease.application.model.report.ReportResponse;
import com.virtadity.manease.application.model.report_line.ReportLineResponse;
import com.virtadity.manease.infrastructure.web.dto.report.ReportLineResponseDTO;
import com.virtadity.manease.infrastructure.web.dto.report.ReportResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.ReportDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class ReportAssembler implements RepresentationModelAssembler<ReportResponse, EntityModel<ReportResponseDTO>> {

    private final ReportDTOMapper reportDTOMapper;
    private final RepresentationModelAssembler<ReportLineResponse, EntityModel<ReportLineResponseDTO>>
            reportLineAssembler;

    @Override
    public EntityModel<ReportResponseDTO> toModel(ReportResponse reportResponse) {
        var reportLines = reportLineAssembler.toCollectionModel(reportResponse.reportLineResponseList());
        var reportResponseDTO = reportDTOMapper.toReportResponseDTO(reportResponse, reportLines);
        return EntityModel.of(reportResponseDTO);
    }
}
