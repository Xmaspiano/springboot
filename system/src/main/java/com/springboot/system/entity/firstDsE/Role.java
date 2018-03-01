package com.springboot.system.entity.firstDsE;

import com.springboot.common.entity.BaseIdEntityMYSQL;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Role extends BaseIdEntityMYSQL {
    //角色标识 程序中判断使用,如"admin"
    private String role;
    //角色描述,UI界面显示使用
    private String description;
    //是否可用,如果不可用将不会添加给用户
    private Boolean available = Boolean.FALSE;

}
