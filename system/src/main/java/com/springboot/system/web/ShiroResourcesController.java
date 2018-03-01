package com.springboot.system.web;

import com.springboot.common.util.CommonUtil;
import com.springboot.system.entity.firstDsE.ShiroResources;
import com.springboot.system.service.ShiroResourcesService;
import com.springboot.system.util.AjaxMsgUtil;
import com.springboot.system.util.MsgUtil;
import com.springboot.system.util.MsgUtilNative;
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

@Controller
@RequestMapping(value = {"/shiroResources"})
public class ShiroResourcesController{
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroResourcesController.class);
    private final MsgUtil msgUtil = new MsgUtilNative(ShiroResourcesController.class);

    @Autowired
    private ShiroResourcesService shiroResourcesService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "systemLayout/shiroResources/shiroResources";
    }

    @RequestMapping(value = "/save" )
    @ResponseBody
//    @RequiresPermissions("resources:save:add,edit")
    public Map saveInfo(ShiroResources shiroResources){
        shiroResourcesService.save(shiroResources);
        return AjaxMsgUtil.AjaxMsg(AjaxMsgUtil.SUCCESS, msgUtil.getMsg("saveInfo.success"));
    }

    @RequestMapping(value = "/delete" )
    @ResponseBody
//    @RequiresPermissions("resources:delete")
    public Map deleteInfo(@RequestParam("id") Long id){
        shiroResourcesService.delete(id);
        return AjaxMsgUtil.AjaxMsg(AjaxMsgUtil.SUCCESS, msgUtil.getMsg("deleteInfo.success"));
    }

    @RequestMapping(value = "/date_grid.json" )
    @ResponseBody
    public Map getGridData(){
        Map jsonMap = new HashMap();
        List<ShiroResources> shiroResourcesList = shiroResourcesService.findAll();

        jsonMap.put("rows", CommonUtil.conversionByList(shiroResourcesList));
        return jsonMap;
    }

}
