package com.virtadity.manease.infrastructure.database.dao;

import com.virtadity.manease.AppTestContainersConfig;
import com.virtadity.manease.infrastructure.database.mapper.*;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

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
