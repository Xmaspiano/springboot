package com.springboot.system.service.impl;


import com.springboot.common.service.impl.CommonServiceImpl;
import com.springboot.system.entity.secondDsE.Hrmresource;
import com.springboot.system.repository.secondDS.HrmresourceRepository;
import com.springboot.system.service.HrmresourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AlbertXmas on 2017/1/13.
 */

@Service
@Transactional("secondTransactionManager")
public class HrmresourceServiceImpl
        extends CommonServiceImpl<Hrmresource, HrmresourceRepository>
        implements HrmresourceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HrmresourceServiceImpl.class);

    @Override
//    @CachePut(value = "hrmresourceRetryCache", key = "#id")//数据放入缓存
    public Hrmresource findOne(Long id) {
        return getRepository().findOne(id);
    }

    @Override
    public List<Hrmresource> findByDepartmentid(Long departmentid) {
        return getRepository().findByDepartmentidAndLoginidNotNull(departmentid);
    }

    @Override
//    @CachePut(value = "hrmresourceRetryCache", key = "#loginid")//数据放入缓存
    public Hrmresource findByLoginid(String loginid) {
        return getRepository().findByLoginid(loginid);
    }

//    @Cacheable(value = "hrmresourceRetryCache")//数据放入缓存
    public List<Hrmresource> findByDDC(){
        return getRepository().findByDDC();
    }
}