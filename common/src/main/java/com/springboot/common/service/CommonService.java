package com.springboot.common.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 *
 *
 * @author XmasPiano
 * @date 2018/3/1 上午10:18
 * @param
 * @return
 */

public interface CommonService<E>{

    public E findOne(Long id);

    public List<E> findAll();

    public Page<E> findAll(Pageable pageable);

    public List<E> findAll(Specification<E> spec);

    public Page<E> findAll(Specification<E> spec, Pageable pageable);

    public E save(E e);

    public Iterable<E> save(Iterable<E> entities);

    public void delete(Long id);

    public void delete(Iterable<E> entities);
}
