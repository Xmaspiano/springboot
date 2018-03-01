package com.springboot.common.model;

import lombok.Data;

import java.lang.reflect.Field;

@Data
public abstract class BaseTreeModel<M extends BaseTreeModel,E> extends BaseModel<M,E> {
    public String localParentId = null;

    protected String NullToString(Object obj){
        if(obj == null){
            return "";
        }

        return obj.toString();

    }

    @Override
    public M changeByEntity(E obj){
        M m = super.changeByEntity(obj);
        Class clazz = m.getClass();

        Field[] fl = clazz.getDeclaredFields();
        for(Field field:fl){
            ParentId parentId = field.getAnnotation(ParentId.class);
            if(parentId != null){
                try {
                    field.setAccessible(true);
                    String val = "-1".equals(field.get(m).toString())?null: field.get(m).toString();
                    m.setLocalParentId(val);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return m;
    }

}
