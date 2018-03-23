package com.springboot.system.oa.service.impl;


import com.springboot.common.service.impl.BaseCommonServiceImpl;
import com.springboot.system.oa.entity.secondDsE.Hrmdepartment;
import com.springboot.system.oa.repository.secondDs.HrmdepartmentRepository;
import com.springboot.system.oa.service.HrmdepartmentService;
import com.sun.tools.javac.code.Attribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

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
public class HrmdepartmentServiceImpl
        extends BaseCommonServiceImpl<Hrmdepartment, HrmdepartmentRepository>
        implements HrmdepartmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HrmdepartmentServiceImpl.class);

    /**
     * 查询OA组织架构下的所有部门
     * @return Map 返回包含Tree数据结构的数据
     */
    @Override
    public Map[] findByYXDept() {//DEPT_YX_ALL
        return deptListForMap(findByCommonDeptTree());
    }

    @Cacheable(value = "DEPT_OA_ALL",key = "#root.methodName")
    @Override
    public List<Hrmdepartment> findByCommonDeptTree(){
        List<Hrmdepartment> superDepts = getRepository().findSub1YX();
        List<Hrmdepartment> depts = getRepository().findAll();

        List<Hrmdepartment> removeList = new ArrayList<>();

        Map<Long, Hrmdepartment> deptMap = new HashMap();
        Map<Long, Hrmdepartment> superDeptMap = new HashMap();

        for(Hrmdepartment superDept:superDepts){
            superDeptMap.put(superDept.getId(),superDept);
        }

        for(Hrmdepartment dept:depts){
            if(dept.getCanceled() != null && "1".equals(dept.getCanceled())){
                removeList.add(dept);
            }else {
                deptMap.put(dept.getId(), dept);
            }
        }

        depts.removeAll(removeList);

        Hrmdepartment hrmdept;
        for(Hrmdepartment dept:depts){
            dept = deptMap.get(dept.getId());
            if(dept.getSupdepid() == 0L){

                hrmdept = superDeptMap.get(dept.getSubcompanyid1());
                if(hrmdept != null){
                    hrmdept.getHrmDepartmentList().add(dept);
                }
            }else{

                hrmdept = deptMap.get(dept.getSupdepid());
                if(hrmdept != null) {
                    hrmdept.getHrmDepartmentList().add(dept);
                }
            }
        }

        return new ArrayList(superDeptMap.values());
    }

    /**
     * 查询OA组织架构
     * @return List 返回列表数据结构
     */
    @Override
    @Cacheable(value = "DEPT_YX_ALL",key = "#root.methodName")
    public List<Hrmdepartment> findByCommonDept() {
        List<Hrmdepartment> hrmdepartmentList = getRepository().findSub1YX();
        List<BigDecimal> countTlevel = getRepository().getCountTlevel();

        List<Hrmdepartment> findList;
        List<Hrmdepartment> removeList;

        for(BigDecimal big:countTlevel){
            findList = getRepository().findByTlevel(big.longValue());
            removeList = new ArrayList<>();

            for(Hrmdepartment dept:findList){
                if(dept.getCanceled() != null && "1".equals(dept.getCanceled())){
                    removeList.add(dept);
                }else if(dept.getSupdepid()==0L){
                    dept.setSupdepid(dept.getSubcompanyid1());
                }
            }

            findList.removeAll(removeList);
            hrmdepartmentList.addAll(findList);
        }
        return hrmdepartmentList;
    }

    @Override
    public Map[] findAllBySuper(Long id){
        return deptListForMap(getRepository().findBySupdepid(id));
    }

    private Map[] deptListForMap(List<Hrmdepartment> departmentList){
        Map[] jsonMap = new HashMap[departmentList.size()];
        Hrmdepartment department = null;
        for (int i = 0; i < jsonMap.length; i++) {
            department = departmentList.get(i);

            if("1".equals(department.getCanceled())){
                continue;
            }

            jsonMap[i] = new HashMap(16);

            jsonMap[i].put("id",department.getId());
            jsonMap[i].put("text",department.getDepartmentname());
            if(department.getHrmDepartmentList().size() > 0){
                jsonMap[i].put("children",this.deptListForMap(department.getHrmDepartmentList()));
            }
        }
        return jsonMap;
    }
}