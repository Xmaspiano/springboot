package com.springboot.system.menu.service.cache;


import com.springboot.system.menu.entity.firstDsE.OsMenu;

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
    public List<OsMenu> getMenuListByUser(Long loginid);

    public List<OsMenu> setMenuListByUser(Long loginid, List<OsMenu> menuLists);

    public List<OsMenu> removeMenuListElementByUser(Long loginid, List<OsMenu> removeLists);
}
