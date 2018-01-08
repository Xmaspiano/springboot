package com.springboot.system.web;

import com.springboot.common.util.CommonUtil;
import com.springboot.system.entity.firstDsE.Role;
import com.springboot.system.service.RoleService;
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
@RequestMapping(value = {"/role"})
public class RoleController{
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
    private final MsgUtil msgUtil = new MsgUtilNative(RoleController.class);

    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "systemLayout/role/role";
    }

    @RequestMapping(value = "/roledept", method = RequestMethod.GET)
    public String roledept(){
        return "systemLayout/role/roledept";
    }

    @RequestMapping(value = "/save" )
    @ResponseBody
    @RequiresPermissions("role:save:add,edit")
    public Map saveInfo(Role role){
        roleService.save(role);
        return AjaxMsgUtil.AjaxMsg(AjaxMsgUtil.SUCCESS, msgUtil.getMsg("saveInfo.success"));
    }

    @RequestMapping(value = "/delete" )
    @ResponseBody
    @RequiresPermissions("role:delete")
    public Map deleteInfo(@RequestParam("id") long id){
        roleService.delete(id);
        return AjaxMsgUtil.AjaxMsg(AjaxMsgUtil.SUCCESS, msgUtil.getMsg("deleteInfo.success"));
    }

    @RequestMapping(value = "/date_grid.json" )
    @ResponseBody
    public Map getGridData(){
        Map jsonMap = new HashMap();
        List<Role> roleList = roleService.findAll();

        jsonMap.put("rows", CommonUtil.conversionByList(roleList));
        return jsonMap;
    }

}
