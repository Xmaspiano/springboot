package com.springboot.system.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
