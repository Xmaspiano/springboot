package com.springboot.system.service;


import com.springboot.common.service.CommonService;
import com.springboot.system.entity.secondDsE.Hrmdepartment;

import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano
 * @date 2018/3/1 上午10:26
 * @param   
 * @return   
 */  

public interface HrmdepartmentService extends CommonService<Hrmdepartment> {
    public List<Hrmdepartment> findByYXDept();

    public List<Hrmdepartment> findAllBySuper(Long id);
}
