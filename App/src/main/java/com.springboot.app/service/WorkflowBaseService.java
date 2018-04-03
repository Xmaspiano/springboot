package com.springboot.app.service;

import com.springboot.app.entity.secondDsE.WorkflowBase;
import com.springboot.common.service.CommonService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/3/23 - 上午11:36
 * Created by IntelliJ IDEA.
 */
public interface WorkflowBaseService extends CommonService<WorkflowBase> {
    public List<Long> findCGWorkFlowId();

    }
