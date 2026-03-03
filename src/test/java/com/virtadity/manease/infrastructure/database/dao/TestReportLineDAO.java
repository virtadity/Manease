package com.virtadity.manease.infrastructure.database.dao;

import com.virtadity.manease.AppTestContainersConfig;
import com.virtadity.manease.infrastructure.database.mapper.ReportLineReadModelMapperImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(
        {
                AppTestContainersConfig.class,
                ReportLineDAO.class,
                ReportLineReadModelMapperImpl.class
        }
)
public class TestReportLineDAO {

    @Autowired
    private ReportLineDAO reportLineDAO;

    @Test
    void testReportLineBetweenDates() {
        var fromDate = LocalDateTime.of(2000, 1, 1, 0, 1, 1);
        var toDate = LocalDateTime.now();
        var reportLines = reportLineDAO.getAllBetweenDates(fromDate, toDate);
        assertThat(reportLines)
                .isNotNull()
                .isNotEmpty();
    }
}
