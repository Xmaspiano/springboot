package com.springboot.common.service.impl;


import com.springboot.common.repository.CommonRepository;
import com.springboot.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 *
 * @author XmasPiano
 * @date 2018/3/1 上午10:19
 * @param
 * @return
 */

public abstract class BaseCommonServiceImpl<E, R> implements CommonService<E>{
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseCommonServiceImpl.class);

    @Autowired
    private CommonRepository<E> commonRepository;

    @Override
    @Transactional(readOnly=true)
    public E findOne(Long id) {
        return commonRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<E> findAll() {
        return (List<E>)commonRepository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public Page<E> findAll(Pageable pageable){
        return commonRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly=true)
    public List<E> findAll(Specification<E> spec) {
        return commonRepository.findAll(spec);
    }

    @Override
    @Transactional(readOnly=true)
    public Page<E> findAll(Specification<E> spec, Pageable pageable) {
        return commonRepository.findAll(spec, pageable);
    }

    @Override
    public E save(E e){
        return commonRepository.save(e);
    }

    @Override
    public Iterable<E> save(Iterable<E> entities){
        return commonRepository.save(entities);
    }

    @Override
    public void delete(Long id) {
        commonRepository.delete(commonRepository.findOne(id));
    }

    @Override
    public void delete(Iterable<E> entities) {
        commonRepository.delete(entities);
    }

    public R getRepository() {
        return (R) commonRepository;
    }

}
