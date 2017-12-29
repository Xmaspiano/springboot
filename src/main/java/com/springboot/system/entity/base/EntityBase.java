package com.springboot.system.entity.base;

import javax.persistence.MappedSuperclass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}