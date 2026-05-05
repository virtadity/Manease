package com.virtadity.manease.infrastructure.web.mapper;

import com.virtadity.manease.application.model.report.ReportResponse;
import com.virtadity.manease.application.model.report.line.ReportLineResponse;
import com.virtadity.manease.infrastructure.web.assembler.ReportLineAssembler;
import com.virtadity.manease.infrastructure.web.dto.report.line.ReportLineResponseDTO;
import com.virtadity.manease.infrastructure.web.dto.report.ReportResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestReportDTOMapper {
    private final ReportDTOMapper reportDTOMapper = new ReportDTOMapperImpl();
    private final List<ReportLineResponse> reportResponseList = List.of(
            new ReportLineResponse(
                    LocalDateTime.now().minusHours(70),
                    LocalDateTime.now().minusHours(50),
                    UUID.randomUUID(),
                    BigDecimal.valueOf(60.0),
                    BigDecimal.valueOf(40.0)
            ),

            new ReportLineResponse(
                    LocalDateTime.now().minusHours(60),
                    LocalDateTime.now().minusHours(40),
                    UUID.randomUUID(),
                    BigDecimal.valueOf(40.0),
                    BigDecimal.valueOf(60.0)
            )
    );

    private List<EntityModel<ReportLineResponseDTO>> reportLineResponseEntityModelList;
    private final ReportLineDTOMapper reportLineDTOMapper = new ReportLineDTOMapperImpl();

    @BeforeEach
    public void setUp() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
        ReportLineAssembler reportLineAssembler = new ReportLineAssembler(reportLineDTOMapper);
        reportLineResponseEntityModelList = reportResponseList.stream().map(reportLineAssembler::toModel).toList();
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    public void testMappingToReportDTO() {
        var afterDate = LocalDateTime.now().minusHours(100);
        var beforeDate = LocalDateTime.now().minusHours(10);
        var totalCost = BigDecimal.valueOf(100.0);
        var totalWeight = BigDecimal.valueOf(100.0);

        var reportResponse = new ReportResponse(
                afterDate,
                beforeDate,
                totalCost,
                totalWeight,
                reportResponseList
        );

        var reportResponseDTO = new ReportResponseDTO(
                afterDate,
                beforeDate,
                totalCost,
                totalWeight,
                reportLineResponseEntityModelList
        );

        var actualResponseDTO = reportDTOMapper.toReportResponseDTO(
                reportResponse,
                reportLineResponseEntityModelList
        );

        assertThat(actualResponseDTO).isNotNull().isEqualTo(reportResponseDTO);
        assertThat(actualResponseDTO.getReportLines()).isNotNull().isEqualTo(reportLineResponseEntityModelList);
    }
}
