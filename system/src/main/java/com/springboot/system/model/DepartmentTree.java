package com.springboot.system.model;

import com.springboot.common.model.BaseTreeModel;
import com.springboot.common.model.ParentId;
import com.springboot.system.oa.entity.secondDsE.Hrmdepartment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/3/6 - 下午5:11
 * Created by IntelliJ IDEA.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component("DepartmentTree")
public class DepartmentTree extends BaseTreeModel<DepartmentTree,Hrmdepartment> {
    private Long id;

    private String departmentname;
    private Long subcompanyid1;

    @ParentId
    private Long supdepid;
    private String canceled;
    private String tlevel;

    private List<Hrmdepartment> hrmDepartmentList = new ArrayList<Hrmdepartment>();
}
