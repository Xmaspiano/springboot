package com.springboot.system.resources.web;

import com.springboot.common.util.CommonUtil;
import com.springboot.system.resources.entity.firstDsE.Resources;
import com.springboot.system.resources.service.ResourcesService;
//import com.springboot.common.util.AjaxMsgUtil;
//import com.springboot.common.util.MsgUtilNative;
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
 * @date 2018/3/1 上午10:26
 * @param   
 * @return   
 */  
@Controller
@RequestMapping(value = {"/resources"})
public class ResourcesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesController.class);
    private final MsgUtil msgUtil = new MsgUtilNative(ResourcesController.class);

    @Autowired
    private ResourcesService resourcesService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "systemLayout/resources/ResourcesMenu";
    }

    @RequestMapping(value = "/save" )
    @ResponseBody
    @RequiresPermissions("resources:save")
    public Map saveInfo(Resources resources){
        resourcesService.save(resources);
        return AjaxMsgUtil.ajaxMsg(AjaxMsgUtil.SUCCESS, msgUtil.getMsg("saveInfo.success"));
    }

    @RequestMapping(value = "/delete" )
    @ResponseBody
    @RequiresPermissions("resources:delete")
    public Map deleteInfo(@RequestParam("id") Long id){
        resourcesService.delete(id);
        return AjaxMsgUtil.ajaxMsg(AjaxMsgUtil.SUCCESS, msgUtil.getMsg("deleteInfo.success"));
    }

    @RequestMapping(value = "/date_grid.json" )
    @ResponseBody
    public Map getGridData(){
        Map jsonMap = new HashMap(16);
        List<Resources> osmList = resourcesService.findAll();

        jsonMap.put("rows", CommonUtil.conversionByList(osmList));
        return jsonMap;
    }

    @RequestMapping(value = "/synchronous" )
    @ResponseBody
//    @RequiresPermissions("resources:synchronous")
    public Map synchronous(@RequestParam Long[] id, @RequestParam String[] keyname){
        resourcesService.synchronous(id,keyname);
        return AjaxMsgUtil.ajaxMsg(AjaxMsgUtil.SUCCESS, msgUtil.getMsg("synchronous.success"));
    }

}
