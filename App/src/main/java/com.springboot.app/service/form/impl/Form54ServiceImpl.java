package com.springboot.app.service.form.impl;

import com.springboot.app.entity.secondDsE.Form54;
import com.springboot.app.repository.secondDs.Form54Repository;
import com.springboot.app.service.form.Form54Service;
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
public class Form54ServiceImpl
        extends BaseCommonServiceImpl<Form54, Form54Repository>
        implements Form54Service {

    /**
     * 依据预留单号查询流程主数据
     *
     * @param reservation 预留单号
     * @return
     */
    @Override
    public List<Form54> findByReservation(String reservation) {
        return getRepository().findByReservation(reservation);
    }
}
