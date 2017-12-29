package com.springboot.system.service.cache;

import com.springboot.system.model.MenuList;

import java.util.List;

public interface MenuListCache {
    public List<MenuList> getMenuListByUser(long loginid);

    public List<MenuList> setMenuListByUser(long loginid, List<MenuList> menuLists);

    public List<MenuList> removeMenuListElementByUser(long loginid, List<MenuList> removeLists);
}
