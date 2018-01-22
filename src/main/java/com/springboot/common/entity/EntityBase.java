package com.springboot.common.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.MappedSuperclass;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AlbertXmas on 2017/1/13.
 */

/**
 * 统一定义id的entity基类.
 *
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
 * Oracle需要每个Entity独立定义id的SEQUCENCE时，不继承于本类而改为实现一个Idable的接口。
 */
// JPA 基类的标识
@MappedSuperclass
public abstract class EntityBase {
//	private static final Logger LOGGER = LoggerFactory.getLogger(EntityBase.class);

	private List<Field> getAllFieldByEntity(Class clazz){
		List<Field> fields = new ArrayList<>();
		while (!clazz.isAssignableFrom(Object.class)) {//当父类为null的时候说明到达了最上层的父类(thisect类).
			fields.addAll(Arrays.asList(clazz .getDeclaredFields()));
			clazz = clazz.getSuperclass(); //得到父类,然后赋给自己
		}
		return fields;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Class clazz = this.getClass();
		System.out.println(clazz.toString());
		Object m = null;
		try {
			m = clazz.newInstance();

			List<Field> fields_E = getAllFieldByEntity(this.getClass());
			List<Field> fields_M = getAllFieldByEntity(clazz);

			for(Field field_M:fields_M){
				for(Field field_E:fields_E){
					if(field_E.getName().equals(field_M.getName())){
						field_M.setAccessible(true);
						field_E.setAccessible(true);

						Object val = field_E.get(this);
						if(field_M.getType().isAssignableFrom(String.class) ) {
							field_M.set(m, val == null?"":val.toString());
						}else{
							field_M.set(m, val);
						}
						break;
					}
				}
			}
//			return m;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return m;

//		return super.clone();
	}
}
