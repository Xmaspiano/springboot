package com.springboot.system.repository.firstDs;

import com.springboot.system.entity.firstDsE.Role;
import com.springboot.common.repository.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:28
 * @param   
 * @return   
 */  
public interface RoleRepository extends JpaRepository<Role, Long>,CommonRepository<Role> {

}
