package com.virtadity.manease.controller.implementation;

import com.virtadity.manease.controller.ProductController;
import com.virtadity.manease.dto.ProductDTO;
import com.virtadity.manease.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    @Override
    public List<ProductDTO> all() {
        return productService.all();
    }

    @GetMapping("/{id}")
    @Override
    public Optional<ProductDTO> one(@PathVariable Long id) {
        return productService.one(id);
    }

    @PostMapping("/")
    @Override
    public ProductDTO create(ProductDTO productDTO) {
        return productService.store(productDTO, Optional.empty());
    }

    @PutMapping("/{id}")
    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        return productService.store(productDTO, Optional.of(id));
    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(Long id) {
        productService.delete(id);
    }
}
