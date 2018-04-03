package com.springboot.app.service.form;

import com.springboot.app.entity.secondDsE.Form54;
import com.springboot.common.service.CommonService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/3/23 - 上午11:36
 * Created by IntelliJ IDEA.
 */
public interface Form54Service extends CommonService<Form54> {

    /**
     * 依据预留单号查询流程主数据
     * @param reservation 预留单号
     * @return
     */
    public List<Form54> findByReservation(String reservation);
}
