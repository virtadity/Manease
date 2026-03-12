package com.virtadity.manease.infrastructure.web.assembler;

import com.virtadity.manease.application.model.report.ReportResponse;
import com.virtadity.manease.application.model.report_line.ReportLineResponse;
import com.virtadity.manease.infrastructure.web.dto.report.ReportLineResponseDTO;
import com.virtadity.manease.infrastructure.web.dto.report.ReportResponseDTO;
import com.virtadity.manease.infrastructure.web.mapper.ReportDTOMapper;
import com.virtadity.manease.infrastructure.web.mapper.ReportLineDTOMapper;
import com.virtadity.manease.infrastructure.web.mapper.ReportLineDTOMapperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestReportAssembler {
    @Mock
    private ReportDTOMapper reportDTOMapper;
    private ReportAssembler reportAssembler;

    private final ReportLineDTOMapper reportLineDTOMapper = new ReportLineDTOMapperImpl();

    private final List<ReportLineResponse> reportLineResponseList = List.of(
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

    private CollectionModel<EntityModel<ReportLineResponseDTO>> reportLineResponseDTOCollectionModel;

    @BeforeEach
    public void setUp() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
        ReportLineAssembler reportLineAssembler = new ReportLineAssembler(reportLineDTOMapper);
        reportAssembler = new ReportAssembler(reportDTOMapper, reportLineAssembler);
        reportLineResponseDTOCollectionModel = reportLineAssembler.toCollectionModel(reportLineResponseList);
    }

    @AfterEach
    public void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    public void testReportAssemblingToModel() {
        var afterDate = LocalDateTime.now().minusHours(100);
        var beforeDate = LocalDateTime.now().minusHours(10);
        var totalCost = BigDecimal.valueOf(100.0);
        var totalWeight = BigDecimal.valueOf(100.0);

        var reportResponse = new ReportResponse(
                afterDate,
                beforeDate,
                totalCost,
                totalWeight,
                reportLineResponseList
        );

        var reportResponseDTO = new ReportResponseDTO(
                afterDate,
                beforeDate,
                totalCost,
                totalWeight,
                reportLineResponseDTOCollectionModel
        );

        when(reportDTOMapper.toReportResponseDTO(reportResponse, reportLineResponseDTOCollectionModel))
                .thenReturn(reportResponseDTO);

        var reportModel = reportAssembler.toModel(reportResponse);

        assertThat(reportModel.getContent().getReportLines()).isNotNull().isEqualTo(reportLineResponseDTOCollectionModel);
        assertThat(reportModel.getContent().getAfterDate()).isNotNull().isEqualTo(afterDate);
        assertThat(reportModel.getContent().getBeforeDate()).isNotNull().isEqualTo(beforeDate);
        assertThat(reportModel.getContent().getTotalCost()).isNotNull().isEqualTo(totalCost);
        assertThat(reportModel.getContent().getTotalWeight()).isNotNull().isEqualTo(totalWeight);

        assertThat(reportModel.getLink("self")).isPresent();

        var href = reportModel.getRequiredLink("self").getHref();
        var parameters = UriComponentsBuilder.fromUriString(href).build().getQueryParams();

        var actualAfterDateInHref = parameters.getFirst("after_date");
        var actualBeforeDateInHref = parameters.getFirst("before_date");

        Assertions.assertNotNull(actualAfterDateInHref);
        var actualAfterDate = LocalDateTime.parse(URLDecoder.decode(actualAfterDateInHref, Charset.defaultCharset()));

        Assertions.assertNotNull(actualBeforeDateInHref);
        var actualBeforeDate = LocalDateTime.parse(URLDecoder.decode(actualBeforeDateInHref, Charset.defaultCharset()));

        assertThat(actualAfterDate).isNotNull().isEqualTo(afterDate);
        assertThat(actualBeforeDate).isNotNull().isEqualTo(beforeDate);
    }
}
