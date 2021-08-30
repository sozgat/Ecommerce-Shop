package com.ecommerce.shop.business.service;

import com.ecommerce.shop.business.model.Product;
import com.ecommerce.shop.business.repository.ProductJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductJPARepository productJPARepository;

    public ProductServiceImpl(ProductJPARepository productJPARepository) {
        this.productJPARepository = productJPARepository;
    }

    @Override
    public List<Product> findAll() {
        return productJPARepository.findAll();
    }
}
