package com.ecommerce.shop.business.service;

import com.ecommerce.shop.business.model.Product;
import com.ecommerce.shop.business.repository.BaseJPARepository;
import com.ecommerce.shop.business.repository.ProductJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService{

    public ProductServiceImpl(BaseJPARepository baseJPARepository) {
        super(baseJPARepository);
    }
}
