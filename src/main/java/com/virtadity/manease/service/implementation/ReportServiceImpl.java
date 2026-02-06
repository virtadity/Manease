package com.virtadity.manease.service.implementation;

import com.virtadity.manease.dto.ReportDTO;
import com.virtadity.manease.dto.ReportNoteDTO;
import com.virtadity.manease.repository.PurchaseLineRepository;
import com.virtadity.manease.service.ReportService;
import com.virtadity.manease.service.SupplyService;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    private final SupplyService supplyService;
    private final PurchaseLineRepository purchaseLineRepository;

    public ReportServiceImpl(
            SupplyService supplyService,
            PurchaseLineRepository purchaseLineRepository
    ) {
        this.supplyService = supplyService;
        this.purchaseLineRepository = purchaseLineRepository;
    }

    @Override
    public ReportDTO makeReportBetweenDates(String fromDate, String toDate) {
        var notes = supplyService.getSuppliesBetweenDates(fromDate, toDate).stream().map(supply -> {
            var cost = purchaseLineRepository.countTotalPriceOfSupply(supply.id());
            var weight = purchaseLineRepository.countTotalWeightOfSupply(supply.id());
            return new ReportNoteDTO(supply, cost, weight);
        }).toList();

        var totalCost = notes
                .stream()
                .reduce(
                        0.0f,
                        (partialResult, note ) -> note.price() + partialResult,
                        Float::sum
                );

        var totalWeight = notes
                .stream()
                .reduce(
                        0.0f,
                        (partialResult, note ) -> note.price() + partialResult,
                        Float::sum
                );

        return new ReportDTO(notes, fromDate, toDate, totalCost, totalWeight);
    }
}
