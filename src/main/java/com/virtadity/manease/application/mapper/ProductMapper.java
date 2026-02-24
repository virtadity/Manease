package com.virtadity.manease.application.mapper;

import com.virtadity.manease.application.model.product.ProductRequest;
import com.virtadity.manease.application.model.product.ProductResponse;
import com.virtadity.manease.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    Product toProduct(ProductRequest productRequest);
    List<Product> toProductList(List<ProductRequest> productRequestList);

    ProductResponse toProductResponse(Product product);
    List<ProductResponse> toProductResponseList(List<Product> productList);
}
