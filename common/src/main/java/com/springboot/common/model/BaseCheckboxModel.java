package com.springboot.common.model;
/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:14
 * @param   
 * @return   
 */   
public abstract class BaseCheckboxModel<M extends BaseCheckboxModel,E> extends BaseModel<M,E> {
    boolean checked = false;

    /**
     * 获取复选框对象
     * @param obj
     */
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
