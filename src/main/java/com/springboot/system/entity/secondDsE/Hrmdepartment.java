package com.springboot.system.entity.secondDsE;



import com.springboot.system.entity.base.IdEntity;
import com.springboot.system.entity.firstDsE.OsMenu;
import com.springboot.system.entity.firstDsE.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by AlbertXmas on 2017/1/13.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Hrmdepartment extends IdEntity<Hrmdepartment> {
    private String departmentname;
    private long subcompanyid1;
    private long supdepid;
    private String canceled;
    private String tlevel;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "supdepid", updatable = false)
    private List<Hrmdepartment> hrmDepartmentList = new ArrayList<Hrmdepartment>();
}
