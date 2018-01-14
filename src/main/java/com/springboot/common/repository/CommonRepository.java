package com.springboot.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by AlbertXmas on 2017/1/13.
 */

/***
 *
 * @param <R> repository
 */
@NoRepositoryBean
//@Component
public interface CommonRepository<E> extends JpaRepository<E, Long>,JpaSpecificationExecutor<E> {

}
