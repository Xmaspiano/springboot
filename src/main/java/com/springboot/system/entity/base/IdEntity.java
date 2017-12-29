package com.springboot.system.entity.base;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

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
@Data
public abstract class IdEntity<E> extends EntityBase{//ORACLE
	@Id
//    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
