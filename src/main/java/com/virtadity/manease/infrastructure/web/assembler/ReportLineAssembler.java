package com.virtadity.manease.infrastructure.web.assembler;

import com.virtadity.manease.infrastructure.web.mapper.ReportLineDTOMapper;
import com.virtadity.manease.application.model.report.line.ReportLineResponse;
import com.virtadity.manease.infrastructure.web.dto.report.line.ReportLineResponseDTO;
import com.virtadity.manease.infrastructure.web.controller.PurchaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@Component
public class ReportLineAssembler
        implements RepresentationModelAssembler<ReportLineResponse, EntityModel<ReportLineResponseDTO>> {

    private final ReportLineDTOMapper reportLineDTOMapper;

    @Override
    public EntityModel<ReportLineResponseDTO> toModel(ReportLineResponse reportLineResponse) {
        var reportLineResponseDTO = reportLineDTOMapper.toReportLineResponseDTO(reportLineResponse);
        return EntityModel.of(reportLineResponseDTO,
                linkTo(methodOn(PurchaseController.class).one(reportLineResponse.purchaseId())).withRel("purchase"));
    }
}
