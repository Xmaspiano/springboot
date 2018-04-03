package com.springboot.app.repository.secondDs;

import com.springboot.app.entity.secondDsE.WorkflowBase;
import com.springboot.common.repository.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/3/23 - 上午11:33
 * Created by IntelliJ IDEA.
 */
public interface WorkflowBaseRepository
        extends JpaRepository<WorkflowBase, Long>,CommonRepository<WorkflowBase> {

    @Query(nativeQuery = true, value = " select a.id from workflow_base a " +
            " where a.isvalid = 1 " +
            " and a.workflowname like '%采购%' or a.workflowname like '%领用%' ")
    List<Long> getCGInfo();
}
