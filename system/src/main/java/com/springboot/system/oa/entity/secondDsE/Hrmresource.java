package com.springboot.system.oa.entity.secondDsE;


import com.springboot.common.entity.BaseIdEntity;
import com.springboot.system.entity.firstDsE.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;

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
//@Table(name = "hrmresource")
public class Hrmresource extends BaseIdEntity<Hrmresource> {

    private String loginid;
    private String password;
    private String lastname;
    private char sex;
    private Long departmentid;
    private Long subcompanyid1;
    private String pinyinlastname;

    @Transient
    private Set<Role> roles= new HashSet();
}
