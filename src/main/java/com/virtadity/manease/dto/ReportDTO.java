package com.virtadity.manease.dto;

import java.util.List;

public record ReportDTO(
        List<ReportNoteDTO> notes,
        String fromDate,
        String toDate,
        Float totalCost,
        Float totalWeight
) {}
