package com.ecommerce.shop.business.service;

import com.ecommerce.shop.business.model.AbstractBaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BaseService<T extends AbstractBaseModel>{

    Long count();
    Long count(Specification<? extends AbstractBaseModel> specification);
    T findById(Long primaryKey);
    List<T> findAll();
    Page<T> findAll(Pageable pageable);
    List<T> findAll(Specification<? extends AbstractBaseModel> specification);
    Page<T> findAll(Specification<? extends AbstractBaseModel> specification, Pageable pageable);
    T save(T entity);
    T saveAndFlush(T entity);
    void saveAll(Iterable<T> entities);
    void delete(T entity);
    void deleteById(Long id);



}
