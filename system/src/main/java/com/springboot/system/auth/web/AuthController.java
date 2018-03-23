package com.springboot.system.auth.web;

import com.springboot.common.model.BaseCheckboxModel;
import com.springboot.common.util.CommonUtil;
import com.springboot.common.util.MsgUtil;
import com.springboot.common.util.MsgUtilNative;
import com.springboot.system.auth.entity.AuthType;
import com.springboot.system.auth.entity.firstDsE.Auth;
import com.springboot.system.auth.model.AuthRow;
import com.springboot.system.auth.model.PageAuth;
import com.springboot.system.auth.service.AuthService;
import com.springboot.system.resources.entity.firstDsE.Resources;
import com.springboot.system.resources.entity.firstDsE.ResourcesMenu;
import com.springboot.system.resources.repository.firstDs.ResourcesMenuRepository;
import com.springboot.system.resources.repository.firstDs.ResourcesRepository;
import com.springboot.system.util.AjaxMsgUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:22
 * @param   
 * @return   
 */  
@Controller
@RequestMapping(value = {"/auth"})
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final MsgUtil msgUtil = new MsgUtilNative(AuthController.class);

    @Autowired
    private ResourcesMenuRepository resourcesMenuService;

    @Autowired
    private ResourcesRepository resourcesService;

    @Autowired
    private BaseCheckboxModel pageAuth;

    @Autowired
    private AuthService authService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(
            @RequestParam(value = "deptid", defaultValue = "") Long organizationId,
            @RequestParam(value = "roleid", defaultValue = "") Long jobId,
            @RequestParam(value = "userid", defaultValue = "") Long userId,
            @RequestParam(value = "groupid", defaultValue = "") Long groupId,
            @RequestParam("typeAuth") String typeAuth,
            Model model
    ){
        model.addAttribute("deptid", organizationId);
        model.addAttribute("roleid", jobId);
        model.addAttribute("userid", userId);
        model.addAttribute("groupid", groupId);
        model.addAttribute("typeAuth", typeAuth);
        return "system/auth/Auth";
    }

    @RequestMapping(value = "/date_grid.json" )
    @ResponseBody
    @RequiresPermissions("auth:view")
    public Map getGridData(
            @RequestParam( value = "menuid", defaultValue = "0") Long menuid,
            @RequestParam(value = "deptid", defaultValue = "") Long organizationId,
            @RequestParam(value = "roleid", defaultValue = "") Long jobId,
            @RequestParam(value = "userid", defaultValue = "") Long userId,
            @RequestParam(value = "groupid", defaultValue = "") Long groupId,
            @RequestParam("typeAuth") String typeAuth
    ){
        Map jsonMap = new HashMap(16);
        if(menuid == 0L){
            jsonMap.put("rows",new ResourcesMenu());
            return jsonMap;
        }

        List<Resources> resourcesList = new ArrayList<Resources>();
        resourcesMenuService.findByMenuid(menuid).forEach(resourcesMenu -> resourcesList.add(
                resourcesService.findByKeyname(resourcesMenu.getKeyname())
        ));

        List<PageAuth> pageAuthList = pageAuth.changeByEntitys(resourcesList);

        pageAuthList.forEach(pageAuth -> {
            if(AuthType.getKeyString(AuthType.organization_job).equals(typeAuth)){
                authService.findByMenuidAndOrganizationId(menuid, organizationId)
                        .forEach(auth -> compare(menuid,pageAuth,auth));
            }else if(AuthType.getKeyString(AuthType.user).equals(typeAuth)){
                authService.findByUserId(userId)
                        .forEach(auth -> compare(menuid,pageAuth,auth));
            }
        });

        jsonMap.put("rows", CommonUtil.conversionByList( pageAuthList ));
        return jsonMap;
    }

    @RequestMapping(value = "/save" )
    @ResponseBody
    @RequiresPermissions("auth:save")
    public Map saveInfo(@RequestBody AuthRow authRow){
        Auth auth = new Auth();
        auth.setType(AuthType.getKeyEnum(authRow.getTypeAuth()));
        auth.setMenuid(authRow.getMenuid());
        if(auth.getType() == AuthType.user){
            auth.setUserId(authRow.getUserid());
            authService.delete(
                    authService.findByMenuidAndUserId(authRow.getMenuid(), authRow.getUserid())
            );
        }else if(auth.getType() == AuthType.user_group){
            auth.setGroupId(authRow.getGroupid());
        }else if(auth.getType() == AuthType.organization_job){
            auth.setOrganizationId(authRow.getDeptid());
            auth.setJobId(authRow.getRoleid());
        }else if(auth.getType() == AuthType.organization_group){
            auth.setGroupId(authRow.getGroupid());
            authService.delete(
                    authService.findByMenuidAndOrganizationId(authRow.getMenuid(),authRow.getDeptid())
            );
        }

        List<Auth> saveList = new ArrayList();
        authRow.getChecked().forEach(resources ->{
            Auth temp = null;
            try {
                temp = (Auth) auth.clone();
                temp.setKeyname(resources.getKeyname());
                saveList.add(temp);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
        authService.save(saveList);
        return AjaxMsgUtil.ajaxMsg(AjaxMsgUtil.SUCCESS, msgUtil.getMsg("saveInfo.success"));
    }

    private boolean compare(Long menuid, PageAuth pageAuth, Auth auth){
        if(!menuid.equals(auth.getMenuid())){
            return false;
        }
        if(pageAuth.getKeyname().equals(auth.getKeyname())){
            pageAuth.setChecked(true);
            return true;
        }
        return false;
    }
}
