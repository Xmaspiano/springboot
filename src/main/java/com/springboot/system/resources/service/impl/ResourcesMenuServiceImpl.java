package com.springboot.system.resources.service.impl;

import com.springboot.system.resources.entity.firstDsE.ResourcesMenu;
import com.springboot.system.resources.repository.firstDs.ResourcesMenuRepository;
import com.springboot.system.resources.service.ResourcesMenuService;
import com.springboot.common.service.impl.CommonServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AlbertXmas on 17/8/29.
 */
@Service
@Transactional
public class ResourcesMenuServiceImpl
        extends CommonServiceImpl<ResourcesMenu, ResourcesMenuRepository>
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
