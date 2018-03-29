package com.springboot.system.resources.web;

import com.springboot.common.util.CommonUtil;
import com.springboot.system.resources.entity.firstDsE.Resources;
import com.springboot.system.resources.entity.firstDsE.ResourcesMenu;
import com.springboot.common.model.EffectRow;
import com.springboot.system.resources.service.ResourcesMenuService;
import com.springboot.system.resources.service.ResourcesService;
import com.springboot.system.util.AjaxMsgUtil;
import com.springboot.common.util.MsgUtil;
import com.springboot.common.util.MsgUtilNative;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author XmasPiano
 * @date 2018/3/1 上午10:26
 * @param
 * @return
 */
@Controller
@RequestMapping(value = {"/resourcesmenu"})
public class ResourcesMenuController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesMenuController.class);
    private final MsgUtil msgUtil = new MsgUtilNative(ResourcesMenuController.class);

    @Autowired
    ResourcesMenuService resourcesMenuService;
    @Autowired
    ResourcesService resourcesService;

    @RequestMapping(value = "/date_grid.json" )
    @ResponseBody
    @RequiresPermissions("resourcesmenu:view")

    public Map getGridData(@RequestParam( value = "menuid", defaultValue = "0") Long menuid){
        Map jsonMap = new HashMap(16);
        if(menuid == 0L){
            jsonMap.put("rows",new ResourcesMenu());
            return jsonMap;
        }

        List<Resources> resourcesList = new ArrayList<Resources>();
        List<ResourcesMenu> resourcesMenuList = resourcesMenuService.findByMenuid(menuid);
        for(ResourcesMenu resourcesMenu:resourcesMenuList){
            resourcesList.add(resourcesService.findByKeyname(resourcesMenu.getKeyname()));
        }
        jsonMap.put("rows",CommonUtil.conversionByList(resourcesList));
        return jsonMap;
    }

    @RequestMapping(value = "/save" )
    @ResponseBody
    @RequiresPermissions("resourcesmenu:save")
    public Map saveInfo(@RequestBody EffectRow<Resources> effectRow){
        List<ResourcesMenu> resourcesMenuList = new ArrayList<ResourcesMenu>();

        effectRow.getDeleted().forEach(resources ->{
            resourcesMenuService.deleteByMenuidAndKeyname(effectRow.getMenuid(), resources.getKeyname());
        });

        effectRow.getInserted().forEach(resources ->{
            ResourcesMenu resourcesMenu = new ResourcesMenu();
            resourcesMenu.setMenuid(effectRow.getMenuid());
            resourcesMenu.setKeyname(resources.getKeyname());
            resourcesMenuList.add(resourcesMenu);
        });
        resourcesMenuService.save(resourcesMenuList);

        return AjaxMsgUtil.ajaxMsg(AjaxMsgUtil.SUCCESS, msgUtil.getMsg("saveInfo.success"));
    }
}
