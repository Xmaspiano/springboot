package com.springboot.system.auth.entity.firstDsE;

import com.springboot.common.entity.IdEntityMYSQL;
import com.springboot.system.auth.entity.AuthType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.Set;

/**
 * 组织机构 工作职位  用户  角色 关系表
 * 1、授权的五种情况
 * 只给组织机构授权 (orgnizationId=? and jobId=0)
 * 只给工作职务授权 (orgnizationId=0 and jobId=?)
 * 给组织机构和工作职务都授权 (orgnizationId=? and jobId=?)
 * 给用户授权  (userId=?)
 * 给组授权 (groupId=?)
 * <p/>
 * 因此查询用户有没有权限 就是
 * where (orgnizationId=? and jobId=0) or (organizationId = 0 and jobId=?) or (orgnizationId=? and jobId=?) or (userId=?) or (groupId=?)
 * <p/>
 * <p/>
 * 2、为了提高性能
 * 放到一张表
 * 此处不做关系映射（这样需要配合缓存）
 * <p/>
 * 3、如果另一方是可选的（如只选组织机构 或 只选工作职务） 那么默认0 使用0的目的是为了也让走索引
 * <p/>
 * <p>User: Zhang Kaitao
 * <p>Date: 13-4-24 下午2:14
 * <p>Version: 1.0
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Table(name = "sys_auth")
public class Auth extends IdEntityMYSQL {
    /**
     * 组织机构
     */
    @Column(name = "organization_id")
    private Long organizationId = 0L;

    /**
     * 工作职务
     */
    @Column(name = "job_id")
    private Long jobId = 0L;

    /**
     * 用户
     */
    @Column(name = "user_id")
    private Long userId = 0L;

    /**
     * 组
     */
    @Column(name = "group_id")
    private Long groupId = 0L;

    private Long menuid = 0L;
    private String keyname = "";

    @Enumerated(EnumType.STRING)
    @Transient
    private AuthType type;

}