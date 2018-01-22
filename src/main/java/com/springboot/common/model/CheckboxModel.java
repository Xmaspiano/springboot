package com.springboot.common.model;

public abstract class CheckboxModel<M extends CheckboxModel,E> extends BaseModel<M,E> {
    boolean checked = false;

    public abstract void setChecked(Object obj);

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public M changeByEntity(E obj){
        M m = super.changeByEntity(obj);
//        m.checked = Math.random() >= 0.5d;
        return m;
    }
}
