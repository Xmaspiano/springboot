package com.springboot.system.service;

import com.springboot.system.entity.firstDsE.OsMenu;

import java.util.List;

public interface OsMenuService extends CommonService<OsMenu>{

    public List<OsMenu> findAllBySuper(long id);

    public List<OsMenu> findAllRealLife();

}