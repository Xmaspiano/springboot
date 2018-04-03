package com.springboot.common.model;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 供页面View使用的基础类
 *
 * @author XmasPiano
 * @date  上午9:53
 * @param
 * @return
 */
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

    /**
     * 将entity对象封装成<M>model返回给view
     * @param obj
     * @return M model实体
     */
    public M changeByEntity(E obj){
        try {
            M m = entityClass.newInstance();
            List<Field> fieldsE = getAllFieldByEntity(obj.getClass());
            List<Field> fieldsM = getAllFieldByEntity(entityClass);

            for(Field fieldM:fieldsM){
                for(Field fieldE:fieldsE){
                    if(fieldE.getName().equals(fieldM.getName())){
                        fieldM.setAccessible(true);
                        fieldE.setAccessible(true);

                        Object val = fieldE.get(obj);
                        if(fieldM.getType().isAssignableFrom(String.class) ) {
                            fieldM.set(m, val == null?null:val.toString());
                        }else{
                            fieldM.set(m, val);
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

    /**
     * 将entity列表封装成<M>model返回view
     * @param objList
     * @return List<M> model实体List
     */
    public List<M> changeByEntitys(List<E> objList){
        List<M> mList = new ArrayList<>();
        for(E e:objList){
            mList.add(this.changeByEntity(e));
        }
        return mList;
    }
}
