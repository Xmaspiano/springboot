package com.springboot.system.menu.web;


import com.springboot.common.model.BaseTreeModel;
import com.springboot.common.util.CommonUtil;
import com.springboot.system.menu.entity.firstDsE.OsMenu;
import com.springboot.system.menu.model.PageMenu;
import com.springboot.system.menu.service.OsMenuService;
import com.springboot.system.util.AjaxMsgUtil;
import com.springboot.common.util.MsgUtil;
import com.springboot.common.util.MsgUtilNative;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:23
 * @param   
 * @return   
 */  
@Controller
@RequestMapping(value = {"/menu"})
public class MenuController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);
    private final MsgUtil msgUtil = new MsgUtilNative(MenuController.class);

    @Autowired
    private OsMenuService osMenuService;

    @Autowired
    private PageMenu pageMenu;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "system/menu/Menu";
    }

    @RequestMapping(value = "/save" )
    @ResponseBody
    @RequiresPermissions("menu:save")
    public Map saveInfo(OsMenu osMenu){
        Map jsonMap = new HashMap(16);
        try {
            osMenuService.save(osMenu);
            return AjaxMsgUtil.ajaxMsg(AjaxMsgUtil.SUCCESS, msgUtil.getMsg("deleteInfo.success"));
        }catch(Exception e){
            e.printStackTrace();
            return AjaxMsgUtil.ajaxMsg(AjaxMsgUtil.ERROR, msgUtil.getMsg("deleteInfo.error"));
        }
    }

    @RequestMapping(value = "/delete" )
    @ResponseBody
    @RequiresPermissions("menu:delete")
    public Map deleteInfo(@RequestParam("id") Long id){
        Map jsonMap = new HashMap(16);
        try {
            if(osMenuService.findAllBySuper(id).size() > 0){
                throw new Exception("包含子菜单");
            }
            osMenuService.delete(id);
            return AjaxMsgUtil.ajaxMsg(AjaxMsgUtil.SUCCESS, msgUtil.getMsg("deleteInfo.success"));
        }catch(Exception e){
            e.printStackTrace();
            return AjaxMsgUtil.ajaxMsg(AjaxMsgUtil.ERROR, msgUtil.getMsg("deleteInfo.error"));
        }
    }

    @RequestMapping(value = "/date_treegrid.json" )
    @ResponseBody
    @RequiresPermissions("menu:view")
    public Map getTreeGridData(){
        Map jsonMap = new HashMap(16);
        List<OsMenu> osmList = osMenuService.findAll();
        osmList.stream().forEach(osMenu -> osMenu.getOsMenuList().clear());

//        jsonMap.put("rows", CommonUtil.conversionByList(osmList));
        jsonMap.put("total",osmList.size());
        jsonMap.put("rows", CommonUtil.conversionByList(pageMenu.changeByEntitys(osmList)));
        return jsonMap;
    }
}