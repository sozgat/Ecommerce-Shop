package com.ecommerce.shop.business.service;

import com.ecommerce.shop.business.model.Product;

import java.util.List;

public interface ProductService extends BaseService {

    List<Product> findAll();

}
