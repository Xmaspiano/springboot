package com.springboot.common.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Collection;

/**
 * 统一定义id的entity基类.
 *
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
 * Oracle需要每个Entity独立定义id的SEQUCENCE时，不继承于本类而改为实现一个Idable的接口。
 * @author XmasPiano
 * @date 2018/3/1 上午10:18
 * @param
 * @return
 */

@MappedSuperclass
@Data
public abstract class BaseIdEntityMysql extends BaseEntityBase{
	@Id
//    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

}
