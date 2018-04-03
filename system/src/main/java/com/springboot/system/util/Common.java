package com.springboot.system.util;

import com.springboot.system.oa.service.HrmresourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/4/3 - 下午3:05
 * Created by IntelliJ IDEA.
 */
@Component
public class Common {
    @Autowired
    HrmresourceService hrmresourceService;

    public String getNameById(Long id){
        return hrmresourceService.findOne(id).getLastname();
    }
}
