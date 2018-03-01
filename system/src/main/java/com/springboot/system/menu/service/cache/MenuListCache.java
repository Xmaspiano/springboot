package com.springboot.system.menu.service.cache;

import com.springboot.system.model.MenuList;

import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:24
 * @param   
 * @return   
 */  
public interface MenuListCache {
    public List<MenuList> getMenuListByUser(Long loginid);

    public List<MenuList> setMenuListByUser(Long loginid, List<MenuList> menuLists);

    public List<MenuList> removeMenuListElementByUser(Long loginid, List<MenuList> removeLists);
}
