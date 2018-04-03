package com.springboot.app.service;

import com.springboot.app.entity.secondDsE.RequestBase;
import com.springboot.common.service.CommonService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/3/23 - 上午11:36
 * Created by IntelliJ IDEA.
 */
public interface RequestBaseService extends CommonService<RequestBase> {

    /**
     * 依据采购订单号或者预留单号查询流程
     * @param orderId 采购订单号或者预留单号
     * @return
     */
    public List<RequestBase> findByOrderId(String orderId);

}
