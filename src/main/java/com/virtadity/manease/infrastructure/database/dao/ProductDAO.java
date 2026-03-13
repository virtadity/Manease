package com.virtadity.manease.infrastructure.database.dao;

import com.virtadity.manease.application.port.out.product.*;
import com.virtadity.manease.domain.model.Product;
import com.virtadity.manease.infrastructure.database.dao.exception.ProducerEntityNotFoundException;
import com.virtadity.manease.infrastructure.database.dao.exception.ProductTypeEntityNotFoundException;
import com.virtadity.manease.infrastructure.database.entity.ProducerEntity;
import com.virtadity.manease.infrastructure.database.entity.ProductTypeEntity;
import com.virtadity.manease.infrastructure.database.mapper.ProductEntityMapper;
import com.virtadity.manease.infrastructure.database.repository.ProducerRepository;
import com.virtadity.manease.infrastructure.database.repository.ProductRepository;
import com.virtadity.manease.infrastructure.database.repository.ProductTypeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class ProductDAO implements
        ProductCreateOutputBoundary,
        ProductCorrectOutputBoundary,
        ProductGetOneOutputBoundary,
        ProductGetAllOutputBoundary,
        ProductDeleteOutputBoundary
{
    private final ProductRepository productRepository;
    private final ProducerRepository producerRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ProductEntityMapper productEntityMapper;


    @Override
    public Product create(Product product) {
        var productType = getProductTypeById(product.productTypeId());
        var producer = getProducerById(product.producerId());
        var productEntity = productEntityMapper.toProductEntity(product, productType, producer);
        var createdProductEntity = productRepository.save(productEntity);
        return productEntityMapper.toProduct(createdProductEntity);
    }

    @Override
    @Transactional
    public Product correct(UUID productId, Product product) {
        var productType = getProductTypeById(product.productTypeId());
        var producer = getProducerById(product.producerId());

        var productEntity = productRepository
                .findById(productId)
                .orElseGet(() -> productEntityMapper.toProductEntity(product, productType, producer));

        productEntityMapper.updateFromProduct(productEntity, product, productType, producer);
        var correctedProductEntity = productRepository.save(productEntity);
        return productEntityMapper.toProduct(correctedProductEntity);
    }

    @Override
    public Optional<Product> getOne(UUID productId) {
        return productRepository.findById(productId).map(productEntityMapper::toProduct);
    }

    @Override
    public List<Product> getAll() {
        var productEntityList = productRepository.findAll();
        return productEntityMapper.toProductList(productEntityList);
    }

    @Override
    public void delete(UUID productId) {
        productRepository.deleteById(productId);
    }

    private ProducerEntity getProducerById(UUID producerId) {
        return this.producerRepository
                .findById(producerId)
                .orElseThrow(
                        () -> ProducerEntityNotFoundException.withSuchId(producerId)
                );
    }

    private ProductTypeEntity getProductTypeById(UUID productTypeId) {
        return this.productTypeRepository
                .findById(productTypeId)
                .orElseThrow(
                        () -> ProductTypeEntityNotFoundException.withSuchId(productTypeId)
                );
    }
}
