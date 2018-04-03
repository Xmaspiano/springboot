package com.springboot.app.service.impl;

import com.springboot.app.entity.secondDsE.RequestBase;
import com.springboot.app.repository.secondDs.RequestBaseRepository;
import com.springboot.app.service.RequestBaseService;
import com.springboot.app.service.form.Form234Service;
import com.springboot.app.service.form.Form54Service;
import com.springboot.common.service.impl.BaseCommonServiceImpl;
import com.springboot.system.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/3/23 - 上午11:36
 * Created by IntelliJ IDEA.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RequestBaseServiceImpl
        extends BaseCommonServiceImpl<RequestBase, RequestBaseRepository>
        implements RequestBaseService {
    @Autowired
    private Form54Service form54Service;
    @Autowired
    private Form234Service form234Service;
    @Autowired
    Common common;


    /**
     * 依据采购订单号或者预留单号查询流程
     *
     * @param orderId 采购订单号
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<RequestBase> findByOrderId(String orderId) {
        //预留单号
        final String startsWith0 = "0";
        //采购申请单号
        final String startsWith1 = "1";
        List<RequestBase> retnList = new ArrayList<RequestBase>();

        if(orderId.startsWith(startsWith0)){
            form54Service.findByReservation(orderId).forEach(form54 -> {
                retnList.add(findOne(Long.valueOf(form54.getRequestid())));
            });
        }else if(orderId.startsWith(startsWith1)){
            form234Service.findByNum(orderId).forEach(form234 -> {
                RequestBase requestBase = findOne(Long.valueOf(form234.getRequestid()));
                if(requestBase.getRequestid() != null) {
                    retnList.add(requestBase);
                    if (requestBase.getRequestid() > 0L) {
                        retnList.addAll(getRepository().findByMainrequestid(requestBase.getRequestid()));
                    }
                }
            });
        }
        return retnList;
    }
}
