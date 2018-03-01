package com.springboot.system.resources.service.impl;

import com.springboot.common.service.impl.BaseCommonServiceImpl;
import com.springboot.system.resources.entity.firstDsE.ResourcesMenu;
import com.springboot.system.resources.repository.firstDs.ResourcesMenuRepository;
import com.springboot.system.resources.service.ResourcesMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:27
 * @param   
 * @return   
 */  
@Service
@Transactional
public class ResourcesMenuServiceImpl
        extends BaseCommonServiceImpl<ResourcesMenu, ResourcesMenuRepository>
        implements ResourcesMenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesMenuServiceImpl.class);


    @Override
    public List<ResourcesMenu> findByMenuid(Long menuid) {
        return this.getRepository().findByMenuid(menuid);
    }

    @Override
    public void deleteByMenuidAndKeyname(Long menuid, String keyname) {
        getRepository().deleteByMenuidAndKeyname(menuid, keyname);
    }
}
