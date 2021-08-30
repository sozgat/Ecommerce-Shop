package com.ecommerce.shop.business.service;

import com.ecommerce.shop.business.repository.BaseJPARepository;

public interface BaseService<T extends BaseJPARepository<T, Long>>{

}
