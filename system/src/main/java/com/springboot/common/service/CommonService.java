package com.springboot.common.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
/**
 * Created by AlbertXmas on 2017/1/13.
 */

/****
 *
 * @param <E> entity
 */
public interface CommonService<E>{

    public E findOne(Long id);

    public List<E> findAll();

//    public Page<E> findAll(Pageable pageable);

    public Page<E> findAll(Specification<E> spec, Pageable pageable);

    public E save(E e);

    public Iterable<E> save(Iterable<E> entities);

    public void delete(Long id);

    public void delete(Iterable<E> entities);
}
