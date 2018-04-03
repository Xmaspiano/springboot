package com.springboot.app.service.form.impl;

import com.springboot.app.entity.secondDsE.Form234dt1;
import com.springboot.app.repository.secondDs.Form234dt1Repository;
import com.springboot.app.service.form.Form234dt1Service;
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
public class Form234dt1ServiceImpl
        extends BaseCommonServiceImpl<Form234dt1, Form234dt1Repository>
        implements Form234dt1Service {
}
