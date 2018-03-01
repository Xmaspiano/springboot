package com.springboot.system.service.impl;


import com.springboot.common.service.impl.BaseCommonServiceImpl;
import com.springboot.system.entity.firstDsE.Role;
import com.springboot.system.repository.firstDs.RoleRepository;
import com.springboot.system.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:26
 * @param   
 * @return   
 */  
@Service
public class RoleServiceImpl
        extends BaseCommonServiceImpl<Role, RoleRepository>
        implements RoleService {

        private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

}
