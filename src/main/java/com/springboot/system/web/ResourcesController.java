package com.springboot.system.web;

import com.springboot.system.entity.firstDsE.Resources;
import com.springboot.system.service.ResourcesService;
import com.springboot.system.util.AjaxMsgUtil;
import com.springboot.system.util.MsgUtil;
import com.springboot.system.util.MsgUtilNative;
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

@Controller
@RequestMapping(value = {"/resources"})
public class ResourcesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesController.class);
    private final MsgUtil msgUtil = new MsgUtilNative(ResourcesController.class);

    @Autowired
    private ResourcesService ResourcesService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "systemLayout/Resources/Resources";
    }

    @RequestMapping(value = "/save" )
    @ResponseBody
    @RequiresPermissions("resources:save")
    public Map saveInfo(Resources Resources){
        ResourcesService.save(Resources);
        return AjaxMsgUtil.AjaxMsg(AjaxMsgUtil.SUCCESS, msgUtil.getMsg("saveInfo.success"));
    }

    @RequestMapping(value = "/delete" )
    @ResponseBody
    @RequiresPermissions("resources:delete")
    public Map deleteInfo(@RequestParam("id") long id){
        ResourcesService.delete(id);
        return AjaxMsgUtil.AjaxMsg(AjaxMsgUtil.SUCCESS, msgUtil.getMsg("deleteInfo.success"));
    }

    @RequestMapping(value = "/date_grid.json" )
    @ResponseBody
    public Map getGridData(){
        Map jsonMap = new HashMap();
        List<Resources> osmList = ResourcesService.findAll();

        Map[] treeMapRes = new HashMap[osmList.size()];
        Resources sonVO;
        jsonMap.put("total",treeMapRes.length);
        for (int i = 0; i < treeMapRes.length; i++) {
            treeMapRes[i] = new HashMap();
            sonVO = osmList.get(i);
            treeMapRes[i].put("id",sonVO.getId());
            treeMapRes[i].put("realName",sonVO.getRealName());
            treeMapRes[i].put("name",sonVO.getName());
            treeMapRes[i].put("method",sonVO.getMethod());
            treeMapRes[i].put("shiroAuth",sonVO.getShiroAuth());
            treeMapRes[i].put("available",sonVO.isAvailable());
        }
        jsonMap.put("rows",treeMapRes);
        return jsonMap;
    }

    @RequestMapping(value = "/synchronous" )
    @ResponseBody
//    @RequiresPermissions("resources:synchronous")
    public Map synchronous(@RequestParam long[] id, @RequestParam String[] keyname){
        ResourcesService.synchronous(id,keyname);
        return AjaxMsgUtil.AjaxMsg(AjaxMsgUtil.SUCCESS, msgUtil.getMsg("synchronous.success"));
    }

}
