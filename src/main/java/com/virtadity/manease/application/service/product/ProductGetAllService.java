package com.virtadity.manease.application.service.product;

import com.virtadity.manease.application.mapper.ProductMapper;
import com.virtadity.manease.application.model.product.ProductResponse;
import com.virtadity.manease.application.port.in.product.ProductGetAllInputBoundary;
import com.virtadity.manease.application.port.out.product.ProductGetAllOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductGetAllService implements ProductGetAllInputBoundary {
    private final ProductGetAllOutputBoundary productStorageGetAll;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> execute() {
        var productsList = productStorageGetAll.getAll();
        return productMapper.toProductResponseList(productsList);
    }
}
