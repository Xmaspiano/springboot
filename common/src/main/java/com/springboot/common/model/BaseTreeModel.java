package com.springboot.common.model;

import com.springboot.common.util.NullIgnore;
import lombok.Data;

import java.lang.reflect.Field;
/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:14
 * @param   
 * @return   
 */  
@Data
public abstract class BaseTreeModel<M extends BaseTreeModel,E> extends BaseModel<M,E> {
//    public String localParentId = null;
    @NullIgnore
    public Integer _parentId = null;

    protected String nullToString(Object obj){
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
                    String val = field.get(m) == null?null: field.get(m).toString();
                    //根节点
                    if("-1".equals(val) || "0".equals(val)) {
                        m.set_parentId(null);
                    }else {
                        m.set_parentId(Integer.valueOf(val));
                    }
//                    m.setLocalParentId(val);
//                    m.set_parentId(val);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return m;
    }

}
