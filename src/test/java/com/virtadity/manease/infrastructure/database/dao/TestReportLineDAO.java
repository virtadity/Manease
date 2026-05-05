package com.virtadity.manease.infrastructure.database.dao;

import com.virtadity.manease.domain.model.ReportLine;
import com.virtadity.manease.infrastructure.database.entity.PurchaseLineEntity;
import com.virtadity.manease.infrastructure.database.repository.PurchaseLineRepository;
import com.virtadity.manease.infrastructure.database.repository.PurchaseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceTestSetting.class)
public class TestReportLineDAO {

    @Autowired
    private ReportLineDAO reportLineDAO;

    @Autowired
    private PurchaseLineRepository purchaseLineRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Test
    void testReportLineBetweenDates() {
        var afterDate = LocalDateTime.of(2000, 1, 1, 0, 1, 1);
        var beforeDate = LocalDateTime.now();

        var purchaseLines = getPurchaseLineBetweenDates(afterDate, beforeDate);
        var expectedCost = countTotalCost(purchaseLines);
        var expectedWeight = countTotalWeight(purchaseLines);

        var reportLines = reportLineDAO.getAllBetweenDates(afterDate, beforeDate);

        assertThat(reportLines).isNotNull().isNotEmpty();

        var actualTotalCost = reportLines
                .stream().map(ReportLine::totalPurchaseCost).reduce(BigDecimal.ZERO, BigDecimal::add);

        var actualTotalWeight = reportLines
                .stream().map(ReportLine::totalPurchaseWeight).reduce(BigDecimal.ZERO, BigDecimal::add);
        
        assertThat(actualTotalCost).usingComparator(BigDecimal::compareTo).isEqualTo(expectedCost);
        assertThat(actualTotalWeight).usingComparator(BigDecimal::compareTo).isEqualTo(expectedWeight);
    }

    /**
     *  get purchase line between dates
     */
    private List<PurchaseLineEntity> getPurchaseLineBetweenDates(LocalDateTime afterDate, LocalDateTime beforeDate) {
        return purchaseRepository.findAll().stream().filter(
            purchaseEntity -> {
                var isAfterDate = purchaseEntity.getDeliveryDate().isAfter(afterDate);
                var isBeforeDate = purchaseEntity.getDeliveryDate().isBefore(beforeDate);
                return isBeforeDate && isAfterDate;
            }
        ).flatMap(purchaseEntity ->
                purchaseLineRepository
                        .getPurchaseLinesOfPurchase(purchaseEntity.getId())
                        .stream()
        ).toList();
    }

    /**
     * count total cost of purchase lines
     */
    private BigDecimal countTotalCost(List<PurchaseLineEntity> purchaseLineEntityList) {
        return purchaseLineEntityList.stream().map(purchaseLineEntity -> {
            var price = purchaseLineEntity.getPrice();
            var quantity = purchaseLineEntity.getQuantity();
            var bigDecimalQuantity = new BigDecimal(quantity);
            return price.multiply(bigDecimalQuantity);
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * count total weight of purchase lines
     */
    private BigDecimal countTotalWeight(List<PurchaseLineEntity> purchaseLineEntityList) {
        return purchaseLineEntityList.stream().map(purchaseLineEntity -> {
            var product = purchaseLineEntity.getProduct();
            var weight = product.getWeight();
            var quantity = purchaseLineEntity.getQuantity();
            var bigDecimalQuantity = new BigDecimal(quantity);
            return weight.multiply(bigDecimalQuantity);
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
