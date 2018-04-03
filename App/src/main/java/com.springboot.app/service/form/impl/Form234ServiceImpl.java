package com.springboot.app.service.form.impl;

import com.springboot.app.entity.secondDsE.Form234;
import com.springboot.app.repository.secondDs.Form234Repository;
import com.springboot.app.service.form.Form234Service;
import com.springboot.common.service.impl.BaseCommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class Form234ServiceImpl
        extends BaseCommonServiceImpl<Form234, Form234Repository>
        implements Form234Service {

    /**
     * 依据采购申请单号查询表单主数据
     * @param num 采购申请单号
     * @return
     */
    @Override
    public List<Form234> findByNum(String num) {
        return getRepository().findByNum(num);
    }
}
