package com.virtadity.manease.infrastructure.database.dao;

import com.virtadity.manease.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Import(PersistenceTestSetting.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestProductDAO {

    @Autowired
    private ProductDAO productDAO;

    private final UUID producerId = UUID.fromString("6d26ba51-2537-470c-8f99-384d20d138e6");
    private final UUID productTypeId = UUID.fromString("0b4b01a2-c8c4-4c8d-8f0c-f4832f8cb19e");


    @Test
    void testCreateProduct() {
        var productId = UUID.randomUUID();
        var name = "Golden";
        var weight = BigDecimal.valueOf(100.0);
        var product = new Product(productId, name, weight, producerId, productTypeId);
        var createdProduct = productDAO.create(product);

        assertThat(createdProduct)
                .isNotNull()
                .isEqualTo(product);
    }

    @Test
    void testGetOneProduct() {
        var product = createProduct();
        var productId = product.productId();

        var productById = productDAO.getOne(productId);
        assertThat(productById)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(Optional.of(product));
    }

    private Product createProduct() {
        var productId = UUID.randomUUID();
        var name = "Golden";
        var weight = BigDecimal.valueOf(100.0);
        var product = new Product(productId, name, weight, producerId, productTypeId);
        return productDAO.create(product);
    }

    @Test
    void testGetAllProductList() {
        var productList = productDAO.getAll();
        assertThat(productList)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void testCorrectProduct() {
        var product = createProduct();
        var productId = product.productId();
        var productForCorrection = new Product(
                productId,
                "White",
                BigDecimal.valueOf(120.0),
                producerId,
                productTypeId
        );

        var correctedProduct = productDAO.correct(productId, productForCorrection);

        assertThat(correctedProduct)
                .isNotNull()
                .isEqualTo(productForCorrection);
    }

    @Test
    void testDeleteProduct() {
        var product = createProduct();
        var productId = product.productId();
        productDAO.delete(productId);

        var deletedProduct = productDAO.getOne(productId);
        assertThat(deletedProduct)
                .isNotNull()
                .isEmpty();
    }
}
