package com.springboot.system.menu.service.cache.impl;

import com.springboot.system.menu.service.cache.MenuListCache;
import com.springboot.system.model.MenuList;
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
    @Cacheable(value = "MENU_LIST", key = "#ploginid")
    public List<MenuList> getMenuListByUser(Long loginid) {
        return null;
    }

    @Override
    @CachePut(value = "MENU_LIST", key = "#loginid")
    public List<MenuList> setMenuListByUser(Long loginid, List<MenuList> menuLists) {
        return null;
    }

    @Override
    @CacheEvict(value = "MENU_LIST",  key = "#loginid")
    public List<MenuList> removeMenuListElementByUser(Long loginid, List<MenuList> removeLists) {
        return null;
    }
}
