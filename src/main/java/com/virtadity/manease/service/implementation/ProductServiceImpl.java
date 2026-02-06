package com.virtadity.manease.service.implementation;

import com.virtadity.manease.dto.ProductDTO;
import com.virtadity.manease.entity.Product;
import com.virtadity.manease.repository.ProductRepository;
import com.virtadity.manease.repository.ProductTypeRepository;
import com.virtadity.manease.service.ProductService;
import com.virtadity.manease.service.exception.ProductTypeNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;

    public ProductServiceImpl(
            ProductRepository productRepository,
            ProductTypeRepository productTypeRepository
    ) {
        this.productRepository = productRepository;
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public List<ProductDTO> all() {
        return productRepository.findAll().stream().map(this::transformToDTO).toList();
    }

    @Override
    public Optional<ProductDTO> one(Long id) {
        return productRepository.findById(id).map(this::transformToDTO);
    }

    @Transactional
    @Override
    public ProductDTO store(ProductDTO productDTO, Optional<Long> id) {
        var product = transformToProduct(productDTO);
        id.ifPresent(product::setId);
        var savedProduct = productRepository.save(product);
        return transformToDTO(savedProduct);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO transformToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getWeight(),
                product.getProductType().getId()
        );
    }

    private Product transformToProduct(ProductDTO productDTO) {
        var product = new Product();

        product.setName(productDTO.name());
        product.setWeight(productDTO.weight());
        var productTypeId = productDTO.productTypeId();

        var productType = productTypeRepository
                .findById(productTypeId)
                .orElseThrow(() -> ProductTypeNotFoundException.withId(productTypeId));

        product.setProductType(productType);
        return product;
    }
}
