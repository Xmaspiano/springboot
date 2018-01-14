package com.springboot.common.model;

import java.util.List;

public abstract class TreeModel<M,E> {
    public String _parentId;

    public abstract M changeByEntity(E obj);

    public abstract List<M> changeByEntitys(List<E> objList);

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
}
