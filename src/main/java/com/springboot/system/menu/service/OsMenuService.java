package com.springboot.system.menu.service;

import com.springboot.common.service.CommonService;
import com.springboot.system.menu.entity.firstDsE.OsMenu;

import java.util.List;

public interface OsMenuService extends CommonService<OsMenu> {

    public List<OsMenu> findAllBySuper(long id);

    public List<OsMenu> findAllRealLife();

}