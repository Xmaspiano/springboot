package com.springboot.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:20
 * @param   
 * @return   
 */  
@NoRepositoryBean
//@Component
public interface CommonRepository<E> extends JpaRepository<E, Long>,JpaSpecificationExecutor<E> {

}
