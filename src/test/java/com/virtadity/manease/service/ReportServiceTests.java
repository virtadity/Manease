package com.virtadity.manease.service;

import com.virtadity.manease.dto.ReportDTO;
import com.virtadity.manease.dto.ReportNoteDTO;
import com.virtadity.manease.dto.SupplyDTO;
import com.virtadity.manease.repository.PurchaseLineRepository;
import com.virtadity.manease.service.implementation.ReportServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ReportServiceTests {
    
    @MockitoBean
    private SupplyService supplyService;

    @MockitoBean
    private PurchaseLineRepository purchaseLineRepository;


    @Test
    void testReportCreation() {
        ReportService reportService = new ReportServiceImpl(supplyService, purchaseLineRepository);

        var fromDate = "2000-1-1";
        var toDate = "2015-1-1";

        var supply1 = new SupplyDTO(
                1L,
                1L,
                "Доставка бананов",
                "2000-1-5",
                "2000-1-1");

        var supply2 = new SupplyDTO(
                2L,
                1L,
                "Доставка апельсинов",
                "2010-1-5",
                "2010-1-1"
        );

        var priceSupply1 = 10.0f;
        var priceSupply2 = 13.0f;

        var weightSupply1 = 1.1f;
        var weightSupply2 = 2.1f;

        var supplies = List.of(supply1, supply2);

        when(supplyService.getSuppliesBetweenDates(fromDate, toDate)).thenReturn(supplies);
        when(purchaseLineRepository.countTotalPriceOfSupply(1L)).thenReturn(priceSupply1);
        when(purchaseLineRepository.countTotalPriceOfSupply(2L)).thenReturn(priceSupply2);
        when(purchaseLineRepository.countTotalWeightOfSupply(1L)).thenReturn(weightSupply1);
        when(purchaseLineRepository.countTotalWeightOfSupply(2L)).thenReturn(weightSupply2);

        var expectedNote1 = new ReportNoteDTO(supply1, weightSupply1, priceSupply1);
        var expectedNote2 = new ReportNoteDTO(supply2, weightSupply2, priceSupply2);

        var expectedNotes = List.of(
                expectedNote1,
                expectedNote2
        );

        var expectedReport = new ReportDTO(
                expectedNotes,
                fromDate,
                toDate,
                priceSupply1 + priceSupply2,
                weightSupply1 + weightSupply2
        );

        var actualReport = reportService.makeReportBetweenDates(fromDate, toDate);

        assertEquals(expectedReport, actualReport);
    }
}
