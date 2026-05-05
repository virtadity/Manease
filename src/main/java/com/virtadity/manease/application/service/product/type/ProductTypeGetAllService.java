package com.virtadity.manease.application.service.product.type;

import com.virtadity.manease.application.mapper.ProductTypeMapper;
import com.virtadity.manease.application.model.product.type.ProductTypeResponse;
import com.virtadity.manease.application.port.in.product.type.ProductTypeGetAllInputBoundary;
import com.virtadity.manease.application.port.out.product.type.ProductTypeGetAllOutputBoundary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductTypeGetAllService  implements ProductTypeGetAllInputBoundary {
    private final ProductTypeGetAllOutputBoundary productTypeStorageGetAll;
    private final ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductTypeResponse> execute() {
        var productTypeList = productTypeStorageGetAll.getAll();
        return productTypeMapper.toProductTypeResponseList(productTypeList);
    }
}
