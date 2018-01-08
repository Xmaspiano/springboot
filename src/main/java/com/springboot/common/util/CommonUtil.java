package com.springboot.common.util;

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

        if(clazz.getSuperclass() != Object.class){
            reMap.putAll(findFieldAndSup(obj, clazz.getSuperclass()));
        }
            Field[] fields = clazz.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    try {
                        Field f = clazz.getDeclaredField(
                                fields[i].getName());
                        f.setAccessible(true);
                        Object o = f.get(obj);
                        reMap.put(fields[i].getName(), o);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        return reMap;
    }
}
