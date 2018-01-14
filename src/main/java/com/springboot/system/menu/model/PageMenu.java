package com.springboot.system.menu.model;

import com.springboot.common.model.TreeModel;
import com.springboot.system.menu.entity.firstDsE.OsMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component("PageMenu")
public class PageMenu extends TreeModel<PageMenu,OsMenu> {
    String id;
    String appid;
    String name;
    String parentid;
    String remark;
    boolean life;
    String dateremark;

    @Override
    public PageMenu changeByEntity(OsMenu osMenu) {
        PageMenu pageMenu = new PageMenu();
        pageMenu.setId(osMenu.getId().toString());
        pageMenu.setAppid(osMenu.getAppid());
        pageMenu.setName(osMenu.getName());
        pageMenu.setParentid(NullToString(osMenu.getParentid()));
        pageMenu.setRemark(osMenu.getRemark());
        pageMenu.setLife(osMenu.isLife());
        pageMenu.setDateremark(NullToString(osMenu.getDatemark()));
        if(osMenu.getParentid() != null && osMenu.getParentid() != 1l) {
            pageMenu.set_parentId(NullToString(osMenu.getParentid()));
        }
        return pageMenu;
    }

    @Override
    public List changeByEntitys(List<OsMenu> pageMenuList) {
        List<PageMenu> pageMenus = new ArrayList();
        for(OsMenu osMenu:pageMenuList){
            pageMenus.add(changeByEntity(osMenu));
        }
        return pageMenus;
    }
}
