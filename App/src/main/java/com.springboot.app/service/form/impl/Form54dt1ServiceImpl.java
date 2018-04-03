package com.springboot.app.service.form.impl;

import com.springboot.app.entity.secondDsE.Form54dt1;
import com.springboot.app.repository.secondDs.Form54dt1Repository;
import com.springboot.app.service.form.Form54dt1Service;
import com.springboot.common.service.impl.BaseCommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/3/23 - 上午11:36
 * Created by IntelliJ IDEA.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class Form54dt1ServiceImpl
        extends BaseCommonServiceImpl<Form54dt1, Form54dt1Repository>
        implements Form54dt1Service {
}
