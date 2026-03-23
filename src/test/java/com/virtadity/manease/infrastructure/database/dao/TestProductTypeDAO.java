package com.virtadity.manease.infrastructure.database.dao;

import com.virtadity.manease.PersistenceTestSetting;
import com.virtadity.manease.domain.model.ProductType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.UUID;

@Import(PersistenceTestSetting.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestProductTypeDAO {
    @Autowired
    private ProductTypeDAO productTypeDAO;

    @Test
    void testCreation() {
        var productType = new ProductType(UUID.randomUUID(), "Абрикосы");
        var createdProductType = productTypeDAO.create(productType);

        assertThat(createdProductType).isNotNull();
        assertThat(createdProductType).isEqualTo(productType);
    }

    @Test
    void testGetOne() {
        var productType = new ProductType(UUID.randomUUID(), "Абрикосы");
        productTypeDAO.create(productType);

        var productTypeById = productTypeDAO.getOne(productType.productTypeId());

        assertThat(productTypeById).isNotNull();
        assertThat(productTypeById).isNotEmpty();
        assertThat(productTypeById).isEqualTo(Optional.of(productType));
    }

    @Test
    void testGetAll() {
        var productTypeList = productTypeDAO.getAll();

        assertThat(productTypeList).isNotNull();
        assertThat(productTypeList).isNotEmpty();
    }

    @Test
    void testCorrect() {
        var productType = new ProductType(UUID.randomUUID(), "Абрикосы");
        productTypeDAO.create(productType);
        var productTypeForUpdate = new ProductType(productType.productTypeId(), "Бананы");
        var updatedProductType = productTypeDAO.correct(productTypeForUpdate.productTypeId(), productTypeForUpdate);

        assertThat(updatedProductType).isNotNull();
        assertThat(updatedProductType).isEqualTo(productTypeForUpdate);
    }

    @Test
    void testDelete() {
        var productType = new ProductType(UUID.randomUUID(), "Абрикосы");
        productTypeDAO.create(productType);
        productTypeDAO.delete(productType.productTypeId());
        var productTypeAfterDeletion = productTypeDAO.getOne(productType.productTypeId());

        assertThat(productTypeAfterDeletion).isNotNull();
        assertThat(productTypeAfterDeletion).isEmpty();
    }
}
