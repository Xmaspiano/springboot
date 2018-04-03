package com.springboot.system.oa.service.impl;


import com.springboot.common.service.impl.BaseCommonServiceImpl;
import com.springboot.system.oa.entity.secondDsE.Hrmresource;
import com.springboot.system.oa.repository.secondDs.HrmresourceRepository;
import com.springboot.system.oa.service.HrmresourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:26
 * @param   
 * @return   
 */  

@Service
@Transactional("secondTransactionManager")
public class HrmresourceServiceImpl
        extends BaseCommonServiceImpl<Hrmresource, HrmresourceRepository>
        implements HrmresourceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HrmresourceServiceImpl.class);

    @Override
    @CachePut(value = "hrmresourceRetryCache", key = "#id")//数据放入缓存
    @Transactional(readOnly=true)
    public Hrmresource findOne(Long id) {
        return getRepository().findOne(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Hrmresource> findByDepartmentid(Long departmentid) {
        return getRepository().findByDepartmentidAndLoginidNotNull(departmentid);
    }

    @Override
    @CachePut(value = "hrmresourceRetryCache", key = "#loginid")//数据放入缓存
    @Transactional(readOnly=true)
    public Hrmresource findByLoginid(String loginid) {
        return getRepository().findByLoginid(loginid);
    }

    @Cacheable(value = "hrmresourceRetryCache")//数据放入缓存
    @Override
    @Transactional(readOnly=true)
    public List<Hrmresource> findByDDC(){
        return getRepository().findByDDC();
    }
}