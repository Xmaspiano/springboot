package com.springboot.app.service.form;

import com.springboot.app.entity.secondDsE.Form234;
import com.springboot.common.service.CommonService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/3/23 - 上午11:36
 * Created by IntelliJ IDEA.
 */
public interface Form234Service extends CommonService<Form234> {

    /**
     * 依据采购申请单号查询表单主数据
     * @param num 采购申请单号
     * @return
     */
    public List<Form234> findByNum(String num);
}
