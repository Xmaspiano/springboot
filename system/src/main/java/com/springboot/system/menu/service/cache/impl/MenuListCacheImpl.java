package com.springboot.system.menu.service.cache.impl;

import com.springboot.system.menu.entity.firstDsE.OsMenu;
import com.springboot.system.menu.service.cache.MenuListCache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:24
 * @param   
 * @return   
 */  
@Service
public class MenuListCacheImpl implements MenuListCache {
    @Override
    @Cacheable(value = "MENU_LIST", key = "#loginid")
    public List<OsMenu> getMenuListByUser(Long loginid) {
        return null;
    }

    @Override
    @CachePut(value = "MENU_LIST", key = "#loginid")
    public List<OsMenu> setMenuListByUser(Long loginid, List<OsMenu> menuLists) {
        return null;
    }

    @Override
    @CacheEvict(value = "MENU_LIST",  key = "#loginid")
    public List<OsMenu> removeMenuListElementByUser(Long loginid, List<OsMenu> removeLists) {
        return null;
    }
}
