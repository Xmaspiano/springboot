package com.springboot.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:25
 * @param   
 * @return   
 */  
//@Component
public class MsgUtilNative<C> implements MsgUtil<C> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MsgUtilNative.class);
    public Class aClass;

    public MsgUtilNative(Class<?> clazz){
        this.aClass = clazz;
    }

    @Override
    public String getMsg(C object) {
        System.out.println(aClass.getName() + ": " + object.toString());
        return object.toString();
    }
}
