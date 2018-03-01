package com.springboot.common.entity;

import javax.persistence.MappedSuperclass;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 统一定义id的entity基类.
 * JPA 基类的标识
 *
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
 * Oracle需要每个Entity独立定义id的SEQUCENCE时，不继承于本类而改为实现一个Idable的接口。
 * @author XmasPiano
 * @date 2018/3/1 上午10:17
 * @param
 * @return
 */

@MappedSuperclass
public abstract class BaseEntityBase {
//	private static final Logger LOGGER = LoggerFactory.getLogger(EntityBase.class);

	private List<Field> getAllFieldByEntity(Class clazz){
		List<Field> fields = new ArrayList<>();
		//当父类为null的时候说明到达了最上层的父类(thisect类).
		while (!clazz.isAssignableFrom(Object.class)) {
			fields.addAll(Arrays.asList(clazz .getDeclaredFields()));
			//得到父类,然后赋给自己
			clazz = clazz.getSuperclass();
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

			List<Field> fieldsE = getAllFieldByEntity(this.getClass());
			List<Field> fieldsM = getAllFieldByEntity(clazz);

			for(Field fieldM:fieldsM){
				for(Field fieldE:fieldsE){
					if(fieldE.getName().equals(fieldM.getName())){
						fieldM.setAccessible(true);
						fieldE.setAccessible(true);

						Object val = fieldE.get(this);
						if(fieldM.getType().isAssignableFrom(String.class) ) {
							fieldM.set(m, val == null?"":val.toString());
						}else{
							fieldM.set(m, val);
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
