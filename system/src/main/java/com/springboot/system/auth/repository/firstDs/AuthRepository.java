package com.springboot.system.auth.repository.firstDs;

import com.springboot.common.repository.CommonRepository;
import com.springboot.system.auth.entity.firstDsE.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by AlbertXmas on 17/8/29.
 */
public interface AuthRepository
            extends JpaRepository<Auth, Long>,CommonRepository<Auth>{

    public List<Auth> findByMenuidAndOrganizationId(Long menuid, Long organizationId);

    public List<Auth> findByMenuidAndUserId(Long menuid, Long userId);

    public List<Auth> findByUserId(Long userid);

    public Set<String> fingKeyNameByUser(Long userid, Set<Long> groupid, Set<Long> deptid, Set<Long> jobid);
//
//    public Resources findByKeyname(String keyname);
//
//    public Auth findByUserId(Long userId);
//
//    public Auth findByGroupId(Long groupId);
//
//    public Auth findByOrganizationIdAndJobId(Long organizationId, Long jobId);
//
//    ///////////委托给AuthRepositoryImpl实现
//    public Set<Long> findRoleIds(Long userId, Set<Long> groupIds, Set<Long> organizationIds, Set<Long> jobIds, Set<Long[]> organizationJobIds);

}
