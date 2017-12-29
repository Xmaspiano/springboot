package com.springboot.system.repository.firstDs;

import com.springboot.system.entity.firstDsE.Role;
import com.springboot.system.repository.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by AlbertXmas on 16/8/8.
 */
public interface RoleRepository extends JpaRepository<Role, Long>,CommonRepository<Role> {

}
