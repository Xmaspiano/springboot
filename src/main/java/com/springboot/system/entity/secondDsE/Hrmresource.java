package com.springboot.system.entity.secondDsE;



import com.springboot.system.entity.base.IdEntity;
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
 * Created by AlbertXmas on 2017/1/13.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
//@Table(name = "hrmresource")
public class Hrmresource extends IdEntity<Hrmresource> {

    private String loginid;
    private String password;
    private String lastname;
    private char sex;
    private int departmentid;
    private int subcompanyid1;
    private String pinyinlastname;

    @Transient
    private Set<Role> roles= new HashSet();
}
