package com.springboot.common.model;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseModel<M,E>  {
    private Class<M> entityClass;

    /**
     * 这个通常也是hibernate的取得子类class的方法
     */
    public BaseModel() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class) params[0];
    }

    private List<Field> getAllFieldByEntity(Class clazz){
        List<Field> fields = new ArrayList<>();
        //当父类为null的时候说明到达了最上层的父类(Object类).
        while (!clazz.isAssignableFrom(Object.class)) {
            fields.addAll(Arrays.asList(clazz .getDeclaredFields()));
            //得到父类,然后赋给自己
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    public M changeByEntity(E obj){
        try {
            M m = entityClass.newInstance();
            List<Field> fields_E = getAllFieldByEntity(obj.getClass());
            List<Field> fields_M = getAllFieldByEntity(entityClass);

            for(Field field_M:fields_M){
                for(Field field_E:fields_E){
                    if(field_E.getName().equals(field_M.getName())){
                        field_M.setAccessible(true);
                        field_E.setAccessible(true);

                        Object val = field_E.get(obj);
                        if(field_M.getType().isAssignableFrom(String.class) ) {
                            field_M.set(m, val == null?"":val.toString());
                        }else{
                            field_M.set(m, val);
                        }
                        break;
                    }
                }
            }
            return m;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<M> changeByEntitys(List<E> objList){
        List<M> mList = new ArrayList<>();
        for(E e:objList){
            mList.add(changeByEntity(e));
        }
        return mList;
    }
}
