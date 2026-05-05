package com.virtadity.manease.infrastructure.database.dao;


import com.virtadity.manease.application.port.out.product.type.*;
import com.virtadity.manease.domain.model.ProductType;
import com.virtadity.manease.infrastructure.database.mapper.ProductTypeEntityMapper;
import com.virtadity.manease.infrastructure.database.repository.ProductTypeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class ProductTypeDAO implements
        ProductTypeCreateOutputBoundary,
        ProductTypeCorrectOutputBoundary,
        ProductTypeGetAllOutputBoundary,
        ProductTypeGetOneOutputBoundary,
        ProductTypeDeleteOutputBoundary
{

    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeEntityMapper productTypeMapper;

    @Override
    public ProductType create(ProductType productType) {
        var productTypeEntity = productTypeMapper.toProductTypeEntity(productType);
        var createdProductTypeEntity = productTypeRepository.save(productTypeEntity);
        return productTypeMapper.toProductType(createdProductTypeEntity);
    }

    @Override
    @Transactional
    public ProductType correct(UUID productTypeId, ProductType productType) {
        var productTypeEntity = productTypeRepository
                .findById(productTypeId)
                .orElseGet(() -> productTypeMapper.toProductTypeEntity(productType));

        productTypeMapper.updateFromProductType(productTypeEntity, productType);
        var correctedProductTypeEntity = productTypeRepository.save(productTypeEntity);
        return productTypeMapper.toProductType(correctedProductTypeEntity);
    }


    @Override
    @Transactional
    public List<ProductType> getAll() {
        var productTypeEntityList = productTypeRepository.findAll();
        return productTypeMapper.toProductTypeList(productTypeEntityList);
    }


    @Override
    public Optional<ProductType> getOne(UUID productTypeId) {
        var productTypeEntity = productTypeRepository.findById(productTypeId);
        return productTypeEntity.map(productTypeMapper::toProductType);
    }

    @Override
    public void delete(UUID productTypeId) {
        productTypeRepository.deleteById(productTypeId);
    }
}
