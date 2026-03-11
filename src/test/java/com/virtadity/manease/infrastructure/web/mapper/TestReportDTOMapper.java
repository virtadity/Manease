package com.virtadity.manease.infrastructure.web.mapper;

import com.virtadity.manease.application.model.report_line.ReportLineResponse;
import com.virtadity.manease.infrastructure.web.assembler.ReportLineAssembler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TestReportDTOMapper {

    @Mock
    private ReportLineAssembler reportLineAssembler;


    private ReportLineDTOMapper reportLineDTOMapper;

    private final ReportDTOMapper reportDTOMapper = new ReportDTOMapperImpl();

    @Test
    public void testMappingReport() {
        var reportLineIds = List.of(
                UUID.randomUUID(),
                UUID.randomUUID()
        );

        var reportLineResponseList = List.of(
                new ReportLineResponse(
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        reportLineIds.get(0),
                        BigDecimal.valueOf(100.0),
                        BigDecimal.valueOf(100.0)),

                new ReportLineResponse(
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        reportLineIds.get(1),
                        BigDecimal.valueOf(100.0),
                        BigDecimal.valueOf(100.0))
        );

    }
}
