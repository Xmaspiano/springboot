package com.springboot.system.service.impl;


import com.springboot.common.service.impl.BaseCommonServiceImpl;
import com.springboot.system.entity.secondDsE.Hrmdepartment;
import com.springboot.system.repository.secondDS.HrmdepartmentRepository;
import com.springboot.system.service.HrmdepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AlbertXmas on 2017/1/13.
 */

@Service
@Transactional("secondTransactionManager")
public class HrmdepartmentServiceImpl
        extends BaseCommonServiceImpl<Hrmdepartment, HrmdepartmentRepository>
        implements HrmdepartmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HrmdepartmentServiceImpl.class);

    @Override
    /**
     * 查询亚心组织架构下的所有部门@@A#
     */
    @Cacheable(value = "DEPT_YX_ALL",key = "#root.methodName")
    public List<Hrmdepartment> findByYXDept() {//DEPT_YX_ALL
        List<Hrmdepartment> hrmdepartmentList= getRepository().findSub1YX();
        for(Hrmdepartment hrmdepartment:hrmdepartmentList){
            hrmdepartment.setHrmDepartmentList(getRepository().findBySubcompanyid1(hrmdepartment.getId()));
        }
        return hrmdepartmentList;
    }

    @Cacheable(value = "DEPT_YX_ALL",key = "#root.methodName")
    @Override
    public List<Hrmdepartment> findAllBySuper(Long id){
        return getRepository().findBySupdepid(id);
    }
}