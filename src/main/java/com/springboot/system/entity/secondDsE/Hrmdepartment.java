package com.springboot.system.entity.secondDsE;



import com.springboot.common.entity.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private Long subcompanyid1;
    private Long supdepid;
    private String canceled;
    private String tlevel;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "supdepid", updatable = false)
    private List<Hrmdepartment> hrmDepartmentList = new ArrayList<Hrmdepartment>();
}
