package com.springboot.common.model;

import java.lang.reflect.Field;

public abstract class TreeModel<M extends TreeModel,E> extends BaseModel<M,E> {
    public String _parentId = null;

    public String get_parentId() {
        return _parentId;
    }

    public void set_parentId(String _parentId) {
        this._parentId = _parentId;
    }

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
                    m.set_parentId(val);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return m;
    }

}
