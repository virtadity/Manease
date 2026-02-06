package com.virtadity.manease.controller.implementation;

import com.virtadity.manease.controller.ProductTypeController;
import com.virtadity.manease.dto.ProductTypeDTO;
import com.virtadity.manease.service.ProductTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product-types")
public class ProductTypeControllerImpl implements ProductTypeController {

    private final ProductTypeService productTypeService;

    public ProductTypeControllerImpl(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @GetMapping("/")
    @Override
    public List<ProductTypeDTO> all() {
        return this.productTypeService.all();
    }

    @GetMapping("/{id}")
    @Override
    public Optional<ProductTypeDTO> one(@PathVariable Long id) {
        return this.productTypeService.one(id);
    }

    @PostMapping("/")
    @Override
    public ProductTypeDTO create(ProductTypeDTO productTypeDTO) {
        return this.productTypeService.store(productTypeDTO, Optional.empty());
    }

    @PutMapping("/{id}")
    @Override
    public ProductTypeDTO update(@PathVariable Long id, ProductTypeDTO productTypeDTO) {
        return this.productTypeService.store(productTypeDTO, Optional.of(id));
    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable Long id) {
        this.productTypeService.delete(id);
    }
}
