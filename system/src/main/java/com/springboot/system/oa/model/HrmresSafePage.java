package com.springboot.system.oa.model;

import com.springboot.common.model.BaseModel;
import com.springboot.system.oa.entity.secondDsE.Hrmresource;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/4/3 - 上午11:38
 * Created by IntelliJ IDEA.
 */
@Data
@Component("HrmresSafePage")
public class HrmresSafePage extends BaseModel<HrmresSafePage,Hrmresource> {
    private Long id;
    private String loginid;
    private String lastname;
    private char sex;
    private Long departmentid;
    private Long subcompanyid1;
    private String pinyinlastname;
}
