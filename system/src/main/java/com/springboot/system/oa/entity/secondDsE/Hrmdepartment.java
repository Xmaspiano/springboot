package com.springboot.system.oa.entity.secondDsE;


import com.springboot.common.entity.BaseIdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:31
 * @param   
 * @return   
 */  
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Hrmdepartment extends BaseIdEntity<Hrmdepartment> {
    private String departmentname;
    private Long subcompanyid1;
    private Long supdepid;
    private String canceled;
    private Long tlevel;
//
//    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    @JoinColumn(name = "supdepid", updatable = false)
    @Transient
    private List<Hrmdepartment> hrmDepartmentList = new ArrayList<Hrmdepartment>();
}
