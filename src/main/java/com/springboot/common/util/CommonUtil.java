package com.springboot.common.util;

import com.springboot.common.config.shiro.AuthRealm;
import com.springboot.common.model.BaseModel;
import com.springboot.system.entity.secondDsE.Hrmresource;
import com.springboot.system.service.HrmresourceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtil<E> {

    public static Map[] conversionByList(List entityList) {
        Map[] rtnMap = new HashMap[entityList.size()];

        int i = 0;
        for(Object object:entityList){
            rtnMap[i++] = ConvertObjToMap(object);
        }

        return rtnMap;
    }

    /**
     * 实体类对象转换成Map
     * @param obj
     * @return
     */
    public static Map<String, Object> ConvertObjToMap(Object obj) {
        Map<String, Object> reMap = null;
        if (obj == null) {
            return null;
        }

        try {
            reMap = findFieldAndSup(obj, obj.getClass());
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return reMap;
    }

    private static Map<String, Object> findFieldAndSup(Object obj, Class clazz) {
        Map<String, Object> reMap = new HashMap<String, Object>();


        if(!clazz.getSuperclass().isAssignableFrom(BaseModel.class) && !clazz.getSuperclass().isAssignableFrom(Object.class)){
            reMap.putAll(findFieldAndSup(obj, clazz.getSuperclass()));
        }
            Field[] fields = clazz.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    try {
                        Field f = clazz.getDeclaredField(
                                fields[i].getName());
                        f.setAccessible(true);
                        Object val = f.get(obj);
                        if(val != null) {
                            reMap.put(fields[i].getName(), val);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        return reMap;
    }
}
