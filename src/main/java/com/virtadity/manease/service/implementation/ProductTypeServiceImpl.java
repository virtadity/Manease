package com.virtadity.manease.service.implementation;

import com.virtadity.manease.dto.ProductTypeDTO;
import com.virtadity.manease.entity.ProductType;
import com.virtadity.manease.repository.ProductTypeRepository;
import com.virtadity.manease.service.ProductTypeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;

    public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public List<ProductTypeDTO> all() {
        return productTypeRepository
                .findAll()
                .stream()
                .map(this::transformToDTO)
                .toList();
    }

    @Override
    public Optional<ProductTypeDTO> one(Long id) {
        return productTypeRepository.findById(id).map(this::transformToDTO);
    }

    @Transactional
    @Override
    public ProductTypeDTO store(ProductTypeDTO productTypeDTO, Optional<Long> id) {
        var productType = transformToProductType(productTypeDTO);
        id.ifPresent(productType::setId);
        var savedProductType = productTypeRepository.save(productType);
        return this.transformToDTO(savedProductType);
    }

    @Override
    public void delete(Long id) {
        productTypeRepository.deleteById(id);
    }

    private ProductTypeDTO transformToDTO(ProductType productType) {
        return new ProductTypeDTO(productType.getId(), productType.getName());
    }

    private ProductType transformToProductType(ProductTypeDTO productTypeDTO) {
        var productType = new ProductType();
        productType.setName(productTypeDTO.name());
        return productType;
    }
}
