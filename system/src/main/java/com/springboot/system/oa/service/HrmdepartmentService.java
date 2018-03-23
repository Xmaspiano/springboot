package com.springboot.system.oa.service;


import com.springboot.common.service.CommonService;
import com.springboot.system.oa.entity.secondDsE.Hrmdepartment;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author XmasPiano
 * @date 2018/3/1 上午10:26
 * @param
 * @return
 */

public interface HrmdepartmentService extends CommonService<Hrmdepartment> {
    public Map[] findByYXDept();

    List<Hrmdepartment> findByCommonDeptTree();

    public List<Hrmdepartment> findByCommonDept();

    public Map[] findAllBySuper(Long id);
}
