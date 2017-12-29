package com.springboot.system.service.impl;


        import com.springboot.system.entity.firstDsE.Role;
        import com.springboot.system.repository.firstDs.RoleRepository;
        import com.springboot.system.service.RoleService;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl
        extends CommonServiceImpl<Role, RoleRepository>
        implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

}
