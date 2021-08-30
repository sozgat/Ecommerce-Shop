package com.ecommerce.shop.business.service;

import com.ecommerce.shop.business.model.AbstractBaseModel;
import com.ecommerce.shop.business.repository.BaseJPARepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class BaseServiceImpl<T extends AbstractBaseModel> implements BaseService<T> {

    private final BaseJPARepository baseJPARepository;

    public BaseServiceImpl(BaseJPARepository baseJPARepository) {
        this.baseJPARepository = baseJPARepository;
    }

    @Override
    public Long count() {
        return baseJPARepository.count();
    }

    @Override
    public Long count(Specification<? extends AbstractBaseModel> specification) {
        return baseJPARepository.count(specification);
    }

    @Override
    public T findById(Long id) {
        return (T) baseJPARepository.findById(id).orElse(null);
    }

    @Override
    public List<T> findAll() {
        return baseJPARepository.findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return baseJPARepository.findAll(pageable);
    }

    @Override
    public List<T> findAll(Specification<? extends AbstractBaseModel> specification) {
        return baseJPARepository.findAll(specification);
    }

    @Override
    public Page<T> findAll(Specification<? extends AbstractBaseModel> specification, Pageable pageable) {
        return baseJPARepository.findAll(specification,pageable);
    }

    @Override
    public T save(T entity) {
        return (T) baseJPARepository.save(entity);
    }

    @Override
    public T saveAndFlush(T entity) {
        return (T) baseJPARepository.saveAndFlush(entity);
    }

    @Override
    public void saveAll(Iterable<T> entities) {
        baseJPARepository.save(entities);
    }

    @Override
    public void delete(T entity) {
        baseJPARepository.delete(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        baseJPARepository.deleteById(id);
    }
}
