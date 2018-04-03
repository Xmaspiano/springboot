package com.springboot.system.oa.repository.secondDs;

import com.springboot.common.repository.CommonRepository;
import com.springboot.system.oa.entity.secondDsE.Hrmdepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:31
 * @param   
 * @return   
 */  
public interface HrmdepartmentRepository extends JpaRepository<Hrmdepartment,Long>,CommonRepository<Hrmdepartment> {

    @Query(nativeQuery = true, value = "select a.id,a.subcompanyname as departmentname,a.companyid as subcompanyid1," +
            "a.supsubcomid as supdepid,a.canceled,a.tlevel from Hrmsubcompany a where id in (1,29,181)")
    public List<Hrmdepartment> findSub1YX();

    @Query(nativeQuery = true, value = "select a.* from Hrmdepartment a where a.supdepid = 0 and a.Subcompanyid1 = ?")
    public List<Hrmdepartment> findBySubcompanyid1(Long subcompanyid);

    public List<Hrmdepartment> findBySupdepid(Long supdepid);

    public List<Hrmdepartment> findByTlevel(Long tlevel);

    @Query(nativeQuery = true, value = "select DISTINCT a.Tlevel from Hrmdepartment a " +
            "where a.Tlevel is not null order by tlevel asc")
    public List<BigDecimal> getCountTlevel();

    @Query(nativeQuery = true, value =
            " select a.id,a.subcompanyname as departmentname,a.companyid as subcompanyid1, " +
            " a.supsubcomid as supdepid,a.canceled,a.tlevel from Hrmsubcompany a " +
            " where canceled is null or canceled <> 1 ")
    public List<Hrmdepartment> getSubCompany();
}
