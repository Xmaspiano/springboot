package com.springboot.common.util;

import com.springboot.common.model.BaseModel;
import com.springboot.common.model.ParentId;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:14
 * @param   
 * @return   
 */  
public class CommonUtil<E> {

    public static Map[] conversionByList(List entityList) {
        Map[] rtnMap = new HashMap[entityList.size()];

        int i = 0;
        for(Object object:entityList){
            rtnMap[i++] = convertObjToMap(object);
        }

        return rtnMap;
    }

    /**
     * 实体类对象转换成Map
     * @param obj
     * @return
     */
    public static Map<String, Object> convertObjToMap(Object obj) {
        Map<String, Object> reMap = null;
        if (obj == null) {
            return null;
        }

        try {
            reMap = findFieldAndSupM(obj, obj.getClass());
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return reMap;
    }

//    private static Map<String, Object> findFieldAndSup(Object obj, Class clazz) {
//        Map<String, Object> reMap = new HashMap<String, Object>(16);
//
//
//        if(!clazz.getSuperclass().isAssignableFrom(BaseModel.class) && !clazz.getSuperclass().isAssignableFrom(Object.class)){
//            reMap.putAll(findFieldAndSup(obj, clazz.getSuperclass()));
//        }
//            Field[] fields = clazz.getDeclaredFields();
//                for (int i = 0; i < fields.length; i++) {
//                    try {
//                        Field f = clazz.getDeclaredField(
//                                fields[i].getName());
//
//                        f.setAccessible(true);
//                        Object val = f.get(obj);
//                        if(val != null) {
//                            reMap.put(fields[i].getName(), val);
//                        }
////                        else {
////                            NullIgnore nullIgnore = f.getAnnotation(NullIgnore.class);
////                            if(nullIgnore != null){
////
////                            }
////                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//        return reMap;
//    }

    private static Map<String, Object> findFieldAndSupM(Object obj, Class clazz) {
        Map<String, Object> reMap = new HashMap<String, Object>(16);


        if(!clazz.getSuperclass().isAssignableFrom(BaseModel.class) && !clazz.getSuperclass().isAssignableFrom(Object.class)){
            reMap.putAll(findFieldAndSupM(obj, clazz.getSuperclass()));
        }
        Field[] fields = clazz.getDeclaredFields();

        Map<String,String> getMotheds = new HashMap<>();
        StringBuilder getMothed;
        for(Field field:fields){
            getMothed = new StringBuilder("get");
            getMothed.append(field.getName().substring(0,1).toUpperCase()).append(field.getName().substring(1));
            getMotheds.put(field.getName(), getMothed.toString());
        }


        for (int i = 0; i < fields.length; i++) {
            try {
                Method method = clazz.getMethod(getMotheds.get(fields[i].getName()));
                if(method != null ){
                    method.setAccessible(true);
                    Object val = method.invoke(obj);
                    if(val != null) {
                        reMap.put(fields[i].getName(), val);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return reMap;
    }
}
