package com.ecommerce.shop.presentation.api.mapper;

import com.ecommerce.shop.business.model.Product;
import com.ecommerce.shop.presentation.api.dto.model.ProductAPIResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductAPIMapper {

    public static ProductAPIResponseDTO fromDomain(Product product){
        ProductAPIResponseDTO productAPIResponseDTO = new ProductAPIResponseDTO();

        productAPIResponseDTO.setUuid(product.getUuid());
        productAPIResponseDTO.setName(product.getName());
        productAPIResponseDTO.setDescription(product.getDescription());
        productAPIResponseDTO.setPrice(product.getPrice());

        return productAPIResponseDTO;
    }

    public static List<ProductAPIResponseDTO> fromDomain(List<Product> products){
        return products.stream().map(p -> ProductAPIMapper.fromDomain(p)).collect(Collectors.toList());
    }
}

