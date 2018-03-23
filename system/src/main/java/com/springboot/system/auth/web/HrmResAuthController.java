package com.springboot.system.auth.web;

import com.springboot.common.util.CommonUtil;
import com.springboot.system.oa.entity.secondDsE.Hrmresource;
import com.springboot.system.oa.service.HrmresourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(value = {"/auth/user"})
public class HrmResAuthController {
    @Autowired
    private HrmresourceService hrmresourceService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "system/auth/AuthUser";
    }

    @RequestMapping("/date_grid.json")
    @ResponseBody
    @RequiresPermissions("auth.user:view")
    public Map query(@RequestParam( value = "deptid", defaultValue = "0") Long deptid){
        Map jsonMap = new HashMap(16);
        if(deptid == 0L){
            jsonMap.put("rows",new Hrmresource());
            return jsonMap;
        }

        List<Hrmresource> hrmresList = hrmresourceService.findByDepartmentid(deptid);

        jsonMap.put("rows", CommonUtil.conversionByList( hrmresList ));
        return jsonMap;
    }
}