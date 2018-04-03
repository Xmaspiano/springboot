package com.springboot.app.entity.secondDsE;

import com.springboot.common.entity.BaseIdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/3/30 - 上午9:34
 * Created by IntelliJ IDEA.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Table(name = "WORKFLOW_BASE")
public class WorkflowBase extends BaseIdEntity<WorkflowBase> {
    String workflowname;
    String workflowtype;
    String formid;
    String isvalid;
    String subcompanyid;

}
