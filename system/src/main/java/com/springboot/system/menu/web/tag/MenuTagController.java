package com.springboot.system.menu.web.tag;

import com.springboot.system.menu.entity.firstDsE.OsMenu;
import com.springboot.system.menu.service.OsMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = {"/menu/tag"})
public class MenuTagController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuTagController.class);

    @Autowired
    private OsMenuService osMenuService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "system/home/left";
    }

    @RequestMapping(value = "/menu_tree.json" )
    @ResponseBody
    public Map getTreeData(@RequestParam(defaultValue = "1",value = "parentid") Long id){
        //二级菜单
        List<OsMenu> osMenuList = osMenuService.findAllBySuper(getLevelTwoMenu(id).getId());

        Map jsonMap = new HashMap();
        jsonMap.put("total",osMenuList.size());
        jsonMap.put("rows",this.buildTreeMenu(osMenuList));
        return jsonMap;
    }

    @RequestMapping(value = "/menu_tree_url.json" )
    @ResponseBody
    public Map getTreeDataUrl(@RequestParam(defaultValue = "0",value = "id") Long id){
        Map jsonMap = new HashMap();
        jsonMap.put("success", true);

        OsMenu osMenu = osMenuService.findOne(id);
        OsMenu osPartMenu = getLevelTwoMenu(osMenu.getParentid());

        //默认
        if(id == 0L){
            jsonMap.put("title", "首页");
            jsonMap.put("id", 0);
            jsonMap.put("url", "/index");
            jsonMap.put("parentName", "菜单");
            jsonMap.put("parentId", 1);
        }else{
            jsonMap.put("title", osMenu.getName());
            jsonMap.put("id", osMenu.getId());
            jsonMap.put("url", osMenu.getRemark());
            jsonMap.put("parentName", osPartMenu.getName());
            jsonMap.put("parentId", osMenu.getParentid());
        }
        return jsonMap;
    }

    private OsMenu getLevelTwoMenu(Long id){
        OsMenu osPartMenu;
        if(id > 1L) {
            osPartMenu = osMenuService.findOne(id);
            if (osPartMenu.getParentid() != 1L) {
                return getLevelTwoMenu(osPartMenu.getParentid());
            }
        }else {
            osPartMenu = new OsMenu();
            osPartMenu.setId(id);
        }
        return osPartMenu;
    }

    public Map[] buildTreeMenu(List<OsMenu> osMenuList){
        Map[] jsonMap = new HashMap[osMenuList.size()];
        OsMenu osMenu;
        for (int i = 0; i < jsonMap.length; i++) {
            osMenu = osMenuList.get(i);
            jsonMap[i] = new HashMap();

            jsonMap[i].put("id",osMenu.getId());
            jsonMap[i].put("text",osMenu.getName());
            if(osMenu.getOsMenuList().size() > 0){
                jsonMap[i].put("children",this.buildTreeMenu(osMenu.getOsMenuList()));
            }
        }
        return jsonMap;
    }
}