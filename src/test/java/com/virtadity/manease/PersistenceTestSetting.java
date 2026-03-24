package com.virtadity.manease;

import com.virtadity.manease.infrastructure.database.dao.*;
import com.virtadity.manease.infrastructure.database.mapper.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

/**
 * General configuration for DAO classes. It used for common application context between tests.
 */
@TestConfiguration
@Import(
        {
                AppTestContainersConfig.class,

                ProductDAO.class,
                ProductEntityMapperImpl.class,

                ProducerDAO.class,
                ProducerEntityMapperImpl.class,

                ProductTypeDAO.class,
                ProductTypeEntityMapperImpl.class,

                PurchaseDAO.class,
                PurchaseEntityMapperImpl.class,

                PurchaseLineDAO.class,
                PurchaseLineEntityMapperImpl.class,

                ReportLineDAO.class,
                ReportLineReadModelMapperImpl.class
        }
)
public class PersistenceTestSetting {
}
