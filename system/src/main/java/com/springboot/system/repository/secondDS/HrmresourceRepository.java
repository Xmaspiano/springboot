package com.springboot.system.repository.secondDS;

import com.springboot.common.repository.CommonRepository;
import com.springboot.system.entity.secondDsE.Hrmresource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:30
 * @param   
 * @return   
 */  
public interface HrmresourceRepository extends JpaRepository<Hrmresource,Long>,CommonRepository<Hrmresource> {

    public Hrmresource findByLoginid(String loginid);

    @Query(nativeQuery = true, value = "select a.* from hrmresource a\n" +
            " left join (select touser,count(*) as ans from touser group by touser) b on b.touser=a.id \n" +
            "  where a.departmentid in (select b.id from hrmdepartment b where b.id = '2305' or b.supdepid = '2305') and a.loginid is not null order by b.ans desc nulls last,a.lastname")
//    @Query(nativeQuery = true, value = "select * from hrmresource")
    public List<Hrmresource> findByDDC();

    public List<Hrmresource> findByDepartmentidAndLoginidNotNull(Long departmentid);
}
