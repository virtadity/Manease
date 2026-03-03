package com.virtadity.manease.infrastructure.database.mapper;

import com.virtadity.manease.domain.model.ReportLine;
import com.virtadity.manease.infrastructure.database.read_model.ReportLineReadModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReportLineReadModelMapper {

    @Mapping(source = "purchaseId", target = "supplyId")
    ReportLine toReportLine(ReportLineReadModel reportLineReadModel);

    @Mapping(source = "purchaseId", target = "supplyId")
    List<ReportLine> toReportLineList(List<ReportLineReadModel> reportLineReadModelList);
}
