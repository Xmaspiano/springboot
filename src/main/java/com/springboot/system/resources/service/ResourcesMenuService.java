package com.springboot.system.resources.service;

import com.springboot.system.resources.entity.firstDsE.ResourcesMenu;
import com.springboot.common.service.CommonService;

import java.util.List;

/**
 * Created by AlbertXmas on 17/8/29.
 */
public interface ResourcesMenuService extends CommonService<ResourcesMenu> {

    public List<ResourcesMenu> findByMenuid(long menuid);
}
