package com.springboot.app.web;

import com.springboot.system.service.HrmdepartmentService;
import com.springboot.system.service.HrmresourceService;
import com.springboot.common.util.MsgUtil;
import com.springboot.common.util.MsgUtilNative;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:31
 * @param   
 * @return   
 */  
@RestController
@RequestMapping(value = {"/app"})
public class AppIndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppIndexController.class);
    private final MsgUtil msgUtil = new MsgUtilNative(AppIndexController.class);

    @Autowired
    HrmresourceService hrmresourceService;

    @Autowired
    HrmdepartmentService hrmdepartmentService;

    @RequestMapping
    public String index(){
        return "appIndex";
    }

    @ModelAttribute
    public Object newUser() {
        msgUtil.getMsg("============应用到所有@RequestMapping注解方法，在其执行之前把返回值放入Model");
        return new Object();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        msgUtil.getMsg("============应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器");
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public String processUnauthenticatedException(NativeWebRequest request, Exception e) {
//        msgUtil.getMsg("===========应用到所有@RequestMapping注解的方法，在其抛出UnauthenticatedException异常时执行");
//        return "index"; //返回一个逻辑视图名
//    }

}