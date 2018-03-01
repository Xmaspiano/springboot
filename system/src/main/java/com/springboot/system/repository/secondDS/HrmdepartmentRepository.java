package com.springboot.system.repository.secondDS;

import com.springboot.common.repository.CommonRepository;
import com.springboot.system.entity.secondDsE.Hrmdepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    @Query(nativeQuery = true, value = "select a.id,a.subcompanyname as departmentname,a.companyid as subcompanyid1,a.supsubcomid as supdepid,a.canceled,a.tlevel from Hrmsubcompany a where id = 1")
    public List<Hrmdepartment> findSub1YX();

    @Query(nativeQuery = true, value = "select a.* from Hrmdepartment a where a.supdepid = 0 and a.Subcompanyid1 = ?")
    public List<Hrmdepartment> findBySubcompanyid1(Long subcompanyid);

    public List<Hrmdepartment> findBySupdepid(Long findBySupdepid);
}
